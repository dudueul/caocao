// Apply Il2CppDumper script.json to a Ghidra program:
//   - ScriptMethod[]  -> rename/create 163,902 functions (Namespace.Class$$Method)
//   - ScriptString[]  -> label 19,897 string literals
//   - ScriptMetadata[]-> label Il2CppClass structures
//   - ScriptMetadataMethod[] -> label metadata method pointers
// Java GhidraScript (headless-safe, no PyGhidra required).
// Usage: -postScript Il2CppRename.java /abs/path/script.json
// @category Il2Cpp
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ghidra.app.script.GhidraScript;
import ghidra.program.model.address.Address;
import ghidra.program.model.listing.Function;
import ghidra.program.model.listing.FunctionManager;
import ghidra.program.model.symbol.SourceType;

import java.io.File;
import java.io.FileReader;

public class Il2CppRename extends GhidraScript {

    private String safe(String name) {
        String n = name.replace(' ', '-').replace('"', '_').replace('/', '.');
        return n.length() > 180 ? n.substring(0, 180) : n;
    }

    private void renameMethods(JsonArray arr) {
        FunctionManager fm = currentProgram.getFunctionManager();
        Address base = currentProgram.getImageBase();
        int done = 0, skipped = 0;
        for (JsonElement el : arr) {
            JsonObject m = el.getAsJsonObject();
            try {
                long addr = m.get("Address").getAsLong();
                String name = safe(m.get("Name").getAsString());
                Address a = base.add(addr);
                Function fn = fm.getFunctionAt(a);
                if (fn == null) {
                    fn = createFunction(a, null);
                }
                if (fn != null) {
                    fn.setName(name, SourceType.USER_DEFINED);
                } else {
                    createLabel(a, name, true, SourceType.USER_DEFINED);
                }
                done++;
                if (done % 20000 == 0) {
                    println("renamed " + done + "...");
                }
            } catch (Exception ex) {
                skipped++;
            }
        }
        println("methods renamed: " + done + ", skipped: " + skipped);
    }

    private void label(JsonArray arr, String prefix, String field) {
        Address base = currentProgram.getImageBase();
        int done = 0;
        for (JsonElement el : arr) {
            try {
                JsonObject o = el.getAsJsonObject();
                long addr = o.get("Address").getAsLong();
                String name = safe(o.get(field).getAsString());
                createLabel(base.add(addr), prefix + name, true, SourceType.USER_DEFINED);
                done++;
            } catch (Exception ex) {
                // skip malformed entries; counts printed at end
            }
        }
        println(prefix + " labels: " + done);
    }

    @Override
    public void run() throws Exception {
        String[] args = getScriptArgs();
        if (args == null || args.length < 1) {
            throw new IllegalArgumentException("usage: Il2CppRename.java <script.json>");
        }
        File f = new File(args[0]);
        println("loading " + f.getAbsolutePath());
        JsonObject data = JsonParser.parseReader(new FileReader(f)).getAsJsonObject();

        JsonArray methods = data.getAsJsonArray("ScriptMethod");
        if (methods != null) renameMethods(methods);

        JsonArray strings = data.getAsJsonArray("ScriptString");
        if (strings != null) label(strings, "str_", "Name");

        JsonArray meta = data.getAsJsonArray("ScriptMetadata");
        if (meta != null) label(meta, "class_", "Name");

        JsonArray metaMethods = data.getAsJsonArray("ScriptMetadataMethod");
        if (metaMethods != null) label(metaMethods, "mm_", "Name");

        println("ALL DONE");
    }
}
