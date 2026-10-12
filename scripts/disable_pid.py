# Disable the expensive analyzers that don't pay off for IL2CPP dumps.
# Decompiler Parameter ID alone can take hours on a 163k-function binary;
# Il2CppDumper's script.json provides signatures anyway.
# @category Il2Cpp
setAnalysisOption(currentProgram, "Decompiler Parameter ID", "false")
setAnalysisOption(currentProgram, "Embedded Media", "false")
setAnalysisOption(currentProgram, "ASCII Strings", "false")  # already found on import
print("analysis options tuned")
