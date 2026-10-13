// name: GuildWarFlatBuffer.key_building_history$$Createkey_building_history
// entry: 024c3640

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined4
GuildWarFlatBuffer_key_building_history__Createkey_building_history
          (undefined8 param_1,long param_2,undefined4 param_3,undefined4 param_4,undefined4 param_5)

{
  undefined4 uVar1;
  undefined4 auStack_48 [2];
  
  if ((bRam0000000004a0112e & 1) == 0) {
    func_0x010c65d4(0x10fec);
    bRam0000000004a0112e = 1;
  }
  if (param_2 == 0) {
    func_0x010eec64(0);
    FlatBuffers_FlatBufferBuilder__Prep(0,4,0xc,0);
    func_0x010eec64(0);
    FlatBuffers_FlatBufferBuilder__PutInt(0,param_5,0);
    func_0x010eec64(0);
    FlatBuffers_FlatBufferBuilder__PutInt(0,param_4,0);
    func_0x010eec64(0);
    FlatBuffers_FlatBufferBuilder__PutInt(0,param_3,0);
    func_0x010eec64(0);
  }
  else {
    FlatBuffers_FlatBufferBuilder__Prep(param_2,4,0xc,0);
    FlatBuffers_FlatBufferBuilder__PutInt(param_2,param_5,0);
    FlatBuffers_FlatBufferBuilder__PutInt(param_2,param_4,0);
    FlatBuffers_FlatBufferBuilder__PutInt(param_2,param_3,0);
  }
  uVar1 = FlatBuffers_FlatBufferBuilder__get_Offset(param_2,0);
  auStack_48[0] = 0;
  func_0x02485754(auStack_48,uVar1,_mm_Method_FlatBuffers_Offset<key_building_history>__ctor__);
  return auStack_48[0];
}


