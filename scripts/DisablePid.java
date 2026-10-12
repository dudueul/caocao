// Disable expensive analyzers that don't pay for IL2CPP dumps.
// Decompiler Parameter ID alone takes hours on 163k functions; Il2CppDumper's
// script.json supplies signatures. Java GhidraScript: compiles headless
// without PyGhidra (Python) availability.
// @category Il2Cpp
import ghidra.app.script.GhidraScript;

public class DisablePid extends GhidraScript {
    @Override
    public void run() throws Exception {
        // The slow-quartet: each can take hours on a 163k-function IL2CPP
        // binary and none is required (script.json supplies names/signatures).
        setAnalysisOption(currentProgram, "Decompiler Parameter ID", "false");
        setAnalysisOption(currentProgram, "Non-Returning Functions - Discovered", "false");
        setAnalysisOption(currentProgram, "Non-Returning Functions - Stubs", "false");
        setAnalysisOption(currentProgram, "Shared Return Calls", "false");
        setAnalysisOption(currentProgram, "Embedded Media", "false");
        setAnalysisOption(currentProgram, "ASCII Strings", "false");
        println("analysis options tuned (PID/no-return/shared-return/embedded/ascii off)");
    }
}
