// name: GuildWar.Backend.PImpl.SessionImpl$$OnWebGetAuthKey
// entry: 01b321c4

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void GuildWar_Backend_PImpl_SessionImpl__OnWebGetAuthKey(long param_1,long param_2)

{
  undefined8 uVar1;
  ulong uVar2;
  long lVar3;
  
  if ((bRam00000000049fbe06 & 1) == 0) {
    func_0x010c65d4(0xc02c);
    bRam00000000049fbe06 = 1;
  }
  *(undefined1 *)(param_1 + 0x68) = 0;
  *(undefined4 *)(param_1 + 0x6c) = 0;
  if (param_2 != 0) {
    uVar1 = LitJson_JsonData__get_Item(param_2,_str_success,0);
    uVar2 = LitJson_JsonData__op_Explicit(0,uVar1,0);
    if (((uVar2 & 1) != 0) &&
       (lVar3 = LitJson_JsonData__get_Item(param_2,_str_packet,0), lVar3 != 0)) {
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar1 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
      if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar1 = Util__GetData(0,lVar3,_str_authKey,uVar1,0);
      if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0x12a) & 1)
           != 0) &&
         (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo);
      }
      uVar1 = CodeStage_AntiCheat_ObscuredTypes_ObscuredString__op_Implicit(0,uVar1,0);
      *(undefined8 *)(param_1 + 0x60) = uVar1;
      *(undefined1 *)(param_1 + 0xa4) = 1;
      *(undefined4 *)(param_1 + 0x70) = 0;
    }
  }
  return;
}


