package cn.allay.codegen;

import cn.allay.dependence.Identifier;
import com.squareup.javapoet.*;
import lombok.SneakyThrows;
import org.cloudburstmc.nbt.NBTInputStream;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;

import javax.lang.model.element.Modifier;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * Allay Project 2023/3/26
 *
 * @author daoge_cmd | Cool_Loong
 */
public class VanillaBlockIdEnumGen {
    public static void main(String[] args) {
        generate();
    }

    static final Path BLOCK_PALETTE_FILE_PATH = Path.of("Data/unpacked/block_palette.nbt");
    static final List<NbtMap> BLOCK_PALETTE_NBT;
    public static final Map<String, NbtMap> MAPPED_BLOCK_PALETTE_NBT = new HashMap<>();

    static {
        try (var nbtReader = new NBTInputStream(new DataInputStream(new GZIPInputStream(Files.newInputStream(BLOCK_PALETTE_FILE_PATH))))) {
            BLOCK_PALETTE_NBT = ((NbtMap) nbtReader.readTag()).getList("blocks", NbtType.COMPOUND);
            for (var entry : BLOCK_PALETTE_NBT) {
                MAPPED_BLOCK_PALETTE_NBT.put(entry.getString("name"), entry);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final ClassName STRING_CLASS = ClassName.get("java.lang", "String");
    private static final ClassName GETTER_CLASS = ClassName.get("lombok", "Getter");
    private static final String JAVA_DOC = """
            Automatically generated by {@code cn.allay.codegen.VanillaBlockIdEnumGen} <br>
            Allay Project <p>
            @author daoge_cmd | Cool_Loong
            """;
    private static final String PACKAGE_NAME = "cn.allay.api.data";

    @SneakyThrows
    public static void generate() {
        generateDependence();
        generateAPI();
    }

    @SneakyThrows
    public static void generateDependence() {
        var identifierClass = ClassName.get("cn.allay.dependence", "Identifier");
        TypeSpec.Builder codeBuilder = commonBuilder(identifierClass);
        addEnums(codeBuilder);
        var javaFile = JavaFile.builder("cn.allay.dependence", codeBuilder.build()).build();
        Files.writeString(Path.of("Allay-CodeGen/src/main/java/cn/allay/dependence/VanillaBlockId.java"), javaFile.toString());
    }

    @SneakyThrows
    public static void generateAPI() {
        var identifierClass = ClassName.get("cn.allay.api.identifier", "Identifier");
        var blockTypeRegistryClass = ClassName.get("cn.allay.api.block.type", "BlockTypeRegistry");
        var blockTypeClass = ClassName.get("cn.allay.api.block.type", "BlockType");
        TypeSpec.Builder codeBuilder = commonBuilder(identifierClass)
                .addMethod(MethodSpec.methodBuilder("getBlockType")
                        .addModifiers(Modifier.PUBLIC)
                        .addStatement("return $T.getRegistry().get(this.getIdentifier())", blockTypeRegistryClass)
                        .returns(blockTypeClass)
                        .build()
                );
        addEnums(codeBuilder);
        var javaFile = JavaFile.builder(PACKAGE_NAME, codeBuilder.build()).build();
        Files.writeString(Path.of("Allay-API/src/main/java/cn/allay/api/data/VanillaBlockId.java"), javaFile.toString().replace("public BlockType", "public BlockType<?>"));
    }

    private static void addEnums(TypeSpec.Builder codeBuilder) {
        var sortedidentifier = BLOCK_PALETTE_NBT.stream().map(block -> block.getString("name")).sorted(String::compareTo).map(Identifier::new).toList();
        for (var identifier : sortedidentifier) {
            var itemId = CodeGen.BLOCK_ID_TO_ITEM_ID_MAP.get(identifier.toString());
            if (itemId == null) {
                codeBuilder.addEnumConstant(
                        identifier.path().toUpperCase(),
                        TypeSpec.anonymousClassBuilder("$S", identifier.toString()).build()
                );
            } else {
                codeBuilder.addEnumConstant(
                        identifier.path().toUpperCase(),
                        TypeSpec.anonymousClassBuilder("$S, $S", identifier.toString(), itemId).build()
                );
            }
        }
    }

    private static TypeSpec.Builder commonBuilder(ClassName identifierClass) {
        return TypeSpec.enumBuilder("VanillaBlockId")
                .addJavadoc(JAVA_DOC)
                .addModifiers(Modifier.PUBLIC)
                .addField(FieldSpec
                        .builder(identifierClass, "identifier", Modifier.PRIVATE, Modifier.FINAL)
                        .addAnnotation(GETTER_CLASS)
                        .build())
                .addField(FieldSpec
                        .builder(identifierClass, "itemIdentifier", Modifier.PRIVATE, Modifier.FINAL)
                        .addAnnotation(GETTER_CLASS)
                        .build())
                .addMethod(MethodSpec.constructorBuilder()
                        .addParameter(STRING_CLASS, "identifier")
                        .addParameter(STRING_CLASS, "itemIdentifier")
                        .addStatement("this.identifier = new $T(identifier)", identifierClass)
                        .addStatement("this.itemIdentifier = new $T(itemIdentifier)", identifierClass)
                        .build()
                );
    }
}
