// name: Chat.Chatting$$<SendGetChatAuthKey>m__3
// entry: 01d321e0

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__<SendGetChatAuthKey>m__3(long param_1,long param_2)

{
  undefined8 uVar1;
  ulong uVar2;
  long lVar3;
  long *plVar4;
  
  if ((bRam00000000049fcf65 & 1) == 0) {
    func_0x010c65d4(0x25fc);
    bRam00000000049fcf65 = 1;
  }
  if (param_2 != 0) {
    uVar1 = LitJson_JsonData__get_Item(param_2,_str_success,0);
    uVar2 = LitJson_JsonData__op_Explicit(0,uVar1,0);
    if ((uVar2 & 1) != 0) {
      lVar3 = LitJson_JsonData__get_Item(param_2,_str_packet,0);
      if (lVar3 == 0) {
        func_0x010eec64(0);
      }
      plVar4 = (long *)LitJson_JsonData__get_Item(lVar3,_str_chatKey,0);
      if (plVar4 != (long *)0x0) {
        uVar1 = (**(code **)(*plVar4 + 0x160))(plVar4,*(undefined8 *)(*plVar4 + 0x168));
        if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0x12a) &
             1) != 0) &&
           (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0xd8) == 0))
        {
          func_0x010da55c(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo);
        }
        uVar1 = CodeStage_AntiCheat_ObscuredTypes_ObscuredString__op_Implicit(0,uVar1,0);
        *(undefined8 *)(param_1 + 0x40) = uVar1;
      }
    }
  }
  return;
}


