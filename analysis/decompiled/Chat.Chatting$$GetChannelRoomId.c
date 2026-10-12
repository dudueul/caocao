// name: Chat.Chatting$$GetChannelRoomId
// entry: 01d2f1cc

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__GetChannelRoomId(undefined8 param_1,long param_2)

{
  int iVar1;
  undefined8 uVar2;
  long lVar3;
  undefined8 uVar4;
  ulong uVar5;
  int iVar6;
  int iStack_68;
  int iStack_64;
  
  if ((bRam00000000049fcf39 & 1) == 0) {
    func_0x010c65d4(0x25c4);
    bRam00000000049fcf39 = 1;
  }
  uVar2 = Chat_Chatting__get_GetBaseNormalRoomId(param_1);
  iVar6 = 0;
  while( true ) {
    if (param_2 == 0) {
      func_0x010eec64(0);
    }
    iVar1 = System_Collections_Generic_List<tk2dUILayoutItem>__get_Count
                      (param_2,_mm_Method_System_Collections_Generic_List<Room>_get_Count__);
    if (iVar1 <= iVar6) break;
    if (param_2 == 0) {
      func_0x010eec64(0);
    }
    lVar3 = System_Collections_Generic_List<object>__get_Item
                      (param_2,iVar6,_mm_Method_System_Collections_Generic_List<Room>_get_Item__);
    if (lVar3 == 0) {
      func_0x010eec64(0);
    }
    lVar3 = *(long *)(lVar3 + 0x10);
    iStack_64 = iVar6;
    uVar4 = func_0x010fdd08(_class_int_TypeInfo,&iStack_64);
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_string_TypeInfo);
    }
    uVar4 = System_String__Format(0,_str__0___1_,uVar2,uVar4,0);
    if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0x12a) & 1)
         != 0) &&
       (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo);
    }
    uVar4 = CodeStage_AntiCheat_ObscuredTypes_ObscuredString__op_Implicit(0,uVar4,0);
    if (lVar3 == 0) {
      func_0x010eec64(0);
    }
    uVar5 = CodeStage_AntiCheat_ObscuredTypes_ObscuredString__Equals(lVar3,uVar4,0);
    if ((uVar5 & 1) == 0) break;
    iVar6 = iVar6 + 1;
  }
  iStack_68 = iVar6;
  uVar4 = func_0x010fdd08(_class_int_TypeInfo,&iStack_68);
  if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_string_TypeInfo);
  }
  System_String__Format(0,_str__0___1_,uVar2,uVar4,0);
  return;
}


