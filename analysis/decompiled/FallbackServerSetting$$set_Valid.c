// name: FallbackServerSetting$$set_Valid
// entry: 0174b080

void FallbackServerSetting__set_Valid(long param_1,byte param_2)

{
  *(byte *)(param_1 + 0x48) = param_2 & 1;
  return;
}


