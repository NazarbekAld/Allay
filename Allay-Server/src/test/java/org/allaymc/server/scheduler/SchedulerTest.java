package org.allaymc.server.scheduler;

import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.scheduler.task.Task;
import org.allaymc.api.scheduler.taskcreator.TaskCreator;
import org.allaymc.api.utils.GameLoop;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Allay Project 2023/3/12
 *
 * @author daoge_cmd
 */
class SchedulerTest {
    protected static final Scheduler scheduler = new AllayScheduler();

    protected static final TaskCreator MOCK_TASK_CREATOR = new MockTaskCreator();

    @Test
    void testAsync() {
        AtomicLong total = new AtomicLong(0);
        for (int i = 0; i < 1000; i++) {
            scheduler.scheduleDelayed(new Task() {
                @Override
                public boolean onRun() {
                    total.incrementAndGet();
                    return false;
                }

                @Override
                public TaskCreator getTaskCreator() {
                    return MOCK_TASK_CREATOR;
                }
            }, 1, true);
        }
        GameLoop.builder()
                .loopCountPerSec(20)
                .onTick(loop -> {
                    if (scheduler.getRunningTaskCount() == 0)
                        loop.stop();
                    scheduler.tick();
                })
                .build().startLoop();
        assertEquals(1000, total.get());
    }

    @Test
    void testSync() {
        AtomicLong total = new AtomicLong();
        for (int i = 0; i < 1000; i++) {
            scheduler.scheduleDelayed(new Task() {
                @Override
                public boolean onRun() {
                    total.incrementAndGet();
                    return false;
                }

                @Override
                public TaskCreator getTaskCreator() {
                    return MOCK_TASK_CREATOR;
                }
            }, 1);
        }
        GameLoop.builder()
                .loopCountPerSec(20)
                .onTick(loop -> {
                    if (scheduler.getRunningTaskCount() == 0)
                        loop.stop();
                    scheduler.tick();
                })
                .build().startLoop();
        assertEquals(1000, total.get());
    }

    @Test
    void testRepeating() {
        AtomicLong total = new AtomicLong();
        scheduler.scheduleRepeating(new Task() {
            @Override
            public boolean onRun() {
                return total.incrementAndGet() != 1000;
            }

            @Override
            public TaskCreator getTaskCreator() {
                return MOCK_TASK_CREATOR;
            }
        }, 1);
        GameLoop.builder()
                .loopCountPerSec(1000)
                .onTick(loop -> {
                    if (scheduler.getRunningTaskCount() == 0)
                        loop.stop();
                    scheduler.tick();
                })
                .build().startLoop();
        assertEquals(1000, total.get());
    }

    private static class MockTaskCreator implements TaskCreator {
        @Override
        public boolean isValid() {
            return true;
        }
    }
}
