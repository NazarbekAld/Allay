package org.allaymc.server.world;

import lombok.SneakyThrows;
import org.allaymc.api.block.interfaces.wood.BlockWoodBehavior;
import org.allaymc.api.data.VanillaBiomeId;
import org.allaymc.api.server.Server;
import org.allaymc.api.server.ServerSettings;
import org.allaymc.api.world.Difficulty;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldData;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.server.world.chunk.AllayChunk;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;
import org.allaymc.server.world.storage.leveldb.AllayLevelDBWorldStorage;
import org.allaymc.testutils.AllayTestExtension;
import org.apache.commons.io.FileUtils;
import org.joml.Vector3i;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * Allay Project 2023/5/31
 *
 * @author Cool_Loong
 */
@ExtendWith({AllayTestExtension.class, MockitoExtension.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LevelDBWorldStorageTest {
    static Server server = Mockito.mock(Server.class);
    static World mockWorld = Mockito.mock(World.class);
    static AllayLevelDBWorldStorage levelDBWorldStorage;

    @BeforeAll
    static void mockServerSettings() {
        try {
            Files.copy(Path.of("src/test/resources/beworld/level.dat"), Path.of("src/test/resources/beworld/copy/level.dat"), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        levelDBWorldStorage = new AllayLevelDBWorldStorage(Path.of("src/test/resources/beworld"));
        @SuppressWarnings("resource") MockedStatic<Server> serve = Mockito.mockStatic(Server.class);
        serve.when(Server::getInstance).thenReturn(server);
        Mockito.when(server.getVirtualThreadPool()).thenReturn(Executors.newVirtualThreadPerTaskExecutor());
        Mockito.when(server.getServerSettings()).thenReturn(new ServerSettings());
        Mockito.when(mockWorld.getPlayers()).thenReturn(List.of());
    }

    @SneakyThrows
    @Test
    @Order(1)
    void testLoadAllayWorldData() {
        WorldData worldData = levelDBWorldStorage.readWorldData();
        worldData.setWorld(mockWorld);
        Assertions.assertEquals(new Vector3i(0, 32767, 0), worldData.getSpawnPoint());
        Assertions.assertEquals(48549, worldData.getTime());
        Assertions.assertEquals(Difficulty.EASY, worldData.getDifficulty());
        Assertions.assertEquals(1811906518383890446L, worldData.getRandomSeed());
    }

    @SneakyThrows
    @Test
    @Order(2)
    void testWriteAllayWorldData() {
        WorldData worldData = levelDBWorldStorage.readWorldData();
        worldData.setWorld(mockWorld);
        worldData.setSpawnPoint(new Vector3i(1, 1, 1));
        worldData.setTime(1234);
        levelDBWorldStorage.writeWorldData(worldData);
        Assertions.assertEquals(new Vector3i(1, 1, 1), worldData.getSpawnPoint());
        Assertions.assertEquals(1234, worldData.getTime());
    }

    @SneakyThrows
    @Test
    @Order(3)
    void testSaveChunk() {
        AllayUnsafeChunk allayUnsafeChunk = AllayUnsafeChunk.builder().emptyChunk(0, 0, DimensionInfo.OVERWORLD);
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                for (int k = -64; k < 320; k++) {
                    allayUnsafeChunk.setBlockState(i, k, j, BlockWoodBehavior.WOOD_TYPE.getDefaultState());
                    allayUnsafeChunk.setBiome(i, k, j, VanillaBiomeId.FOREST);
                }
                allayUnsafeChunk.setHeight(i, j, 319);
            }
        }
        AllayChunk allayChunk = new AllayChunk(allayUnsafeChunk);
        levelDBWorldStorage.writeChunk(allayChunk).join();
    }

    @SneakyThrows
    @Test
    @Order(4)
    void testReadChunk() {
        Chunk chunk = levelDBWorldStorage.readChunk(0, 0, DimensionInfo.OVERWORLD).join();
        Assertions.assertEquals(BlockWoodBehavior.WOOD_TYPE.getDefaultState(), chunk.getBlockState(0, 55, 0));
        Assertions.assertEquals(VanillaBiomeId.FOREST, chunk.getBiome(0, 55, 0));
        Assertions.assertEquals(319, chunk.getHeight(0, 0));
    }

    @AfterAll
    static void end() {
        try {
            levelDBWorldStorage.close();
            Files.copy(Path.of("src/test/resources/beworld/copy/level.dat"), Path.of("src/test/resources/beworld/level.dat"), StandardCopyOption.REPLACE_EXISTING);
            FileUtils.deleteDirectory(Path.of("src/test/resources/beworld/db").toFile());
            Server.getInstance().shutdown();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}