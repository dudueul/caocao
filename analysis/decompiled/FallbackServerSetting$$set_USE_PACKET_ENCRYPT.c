// name: FallbackServerSetting$$set_USE_PACKET_ENCRYPT
// entry: 0174b0c4

void FallbackServerSetting__set_USE_PACKET_ENCRYPT(long param_1,byte param_2)

{
  *(byte *)(param_1 + 0x28) = param_2 & 1;
  return;
}


