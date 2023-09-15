package cn.allay.codeupdate;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VanillaBlockInterfaceUpdate {
    public static Path FILE_OUTPUT_PATH_BASE = Path.of("Allay-API/src/main/java/cn/allay/api/block/interfaces");
    public static String formatCode = "\n            ";

    public static void main(String[] args) {
        addCode(".setBlockBaseComponentSupplier((b) -> BlockBaseComponentImpl.builder().place(CommonBlockPlaceFunctions.STAIR_PLACE).build())",
                """
                        import cn.allay.api.block.CommonBlockPlaceFunctions;
                        import cn.allay.api.block.component.base.BlockBaseComponentImpl;""",
                ".*Stairs");
    }

    public static void addCode(String code, String importCode, String classNameRegex) {
        List<String> r = new ArrayList<>();
        Pattern compile = Pattern.compile(classNameRegex);
        File file = FILE_OUTPUT_PATH_BASE.toFile();
        for (var f : Objects.requireNonNull(file.listFiles())) {
            Matcher matcher = compile.matcher(f.getName());
            if (matcher.find()) {
                String s;
                try {
                    s = Files.readString(f.toPath());
                    var result = s.replace(".build();", formatCode + code + formatCode + ".build();");
                    result = result.replace("import cn.allay.api.block.BlockBehavior;", "import cn.allay.api.block.BlockBehavior;\n" + importCode);
                    Files.writeString(f.toPath(), result, StandardCharsets.UTF_8, StandardOpenOption.WRITE);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}