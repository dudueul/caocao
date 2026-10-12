// Decompile functions by name-substring query and write decompiled C to files.
// Usage: -postScript DecompileFuncs.java <query,...> <outdir>
//   query = substring of the function name (e.g. "ServerSetting$$get_")
// Writes one .c file per matching function + a summary index.
// @category Il2Cpp
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ghidra.app.decompiler.*;
import ghidra.app.script.GhidraScript;
import ghidra.program.model.listing.Function;
import ghidra.program.model.listing.FunctionIterator;

import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DecompileFuncs extends GhidraScript {

    @Override
    public void run() throws Exception {
        String[] args = getScriptArgs();
        String[] queries = args[0].split(",");
        String outdir = args[1];
        Files.createDirectories(Paths.get(outdir));

        DecompInterface di = new DecompInterface();
        DecompileOptions opts = new DecompileOptions();
        di.setOptions(opts);
        di.openProgram(currentProgram);

        FunctionIterator it = currentProgram.getFunctionManager().getFunctions(true);
        List<String> index = new ArrayList<>();
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        while (it.hasNext()) {
            Function fn = it.next();
            String name = fn.getName();
            boolean match = false;
            for (String q : queries) {
                if (name.contains(q)) { match = true; break; }
            }
            if (!match) continue;

            DecompileResults res = di.decompileFunction(fn, 60, monitor);
            String c = res != null && res.decompileCompleted()
                    ? res.getDecompiledFunction().getC() : "// decompile failed";
            String safe = name.replaceAll("[^A-Za-z0-9_$.\\\\-]", "_");
            String file = outdir + "/" + safe + ".c";
            try (PrintWriter pw = new PrintWriter(file, StandardCharsets.UTF_8)) {
                pw.println("// name: " + name);
                pw.println("// entry: " + fn.getEntryPoint());
                pw.println(c);
            }
            index.add(name + " @ " + fn.getEntryPoint() + " -> " + safe + ".c");
        }

        try (PrintWriter pw = new PrintWriter(outdir + "/_index.txt", StandardCharsets.UTF_8)) {
            for (String line : index) pw.println(line);
        }
        println("decompiled " + index.size() + " functions for: " + args[0]);
        di.dispose();
    }
}
