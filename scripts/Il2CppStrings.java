// Follow-up: label IL2CPP string literals (ScriptString uses field "Value",
// unlike ScriptMetadata's "Name" — this cost us one pass to learn).
// Usage: -postScript Il2CppStrings.java /abs/path/script.json
// @category Il2Cpp
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ghidra.app.script.GhidraScript;
import ghidra.program.model.address.Address;
import ghidra.program.model.symbol.SourceType;

import java.io.File;
import java.io.FileReader;

public class Il2CppStrings extends GhidraScript {
    @Override
    public void run() throws Exception {
        String[] args = getScriptArgs();
        JsonObject data = JsonParser.parseReader(new FileReader(new File(args[0]))).getAsJsonObject();
        JsonArray strings = data.getAsJsonArray("ScriptString");
        Address base = currentProgram.getImageBase();
        int done = 0, skipped = 0;
        for (JsonElement el : strings) {
            try {
                JsonObject o = el.getAsJsonObject();
                long addr = o.get("Address").getAsLong();
                String value = o.get("Value").getAsString();
                String name = value.replace(' ', '-').replace('"', '_');
                if (name.length() > 180) name = name.substring(0, 180);
                createLabel(base.add(addr), "str_" + name, true, SourceType.USER_DEFINED);
                done++;
            } catch (Exception ex) {
                skipped++;
            }
        }
        println("string labels: " + done + ", skipped: " + skipped);
    }
}
