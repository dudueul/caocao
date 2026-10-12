// name: Chat.Chatting$$JoinToMultiplayWatchChannel
// entry: 01d319e8

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__JoinToMultiplayWatchChannel(long param_1,undefined8 param_2)

{
  uint uVar1;
  ulong uVar2;
  undefined8 uVar3;
  long lVar4;
  undefined8 uVar5;
  long lVar6;
  undefined1 auVar7 [12];
  
  if ((bRam00000000049fcf60 & 1) == 0) {
    func_0x010c65d4(0x25d6);
    bRam00000000049fcf60 = 1;
  }
  lVar4 = *(long *)(param_1 + 0x48);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  uVar5 = *(undefined8 *)(lVar4 + 0x30);
  if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar1 = System_String__IsNullOrEmpty(0,uVar5,0);
  if ((uVar1 & 1) == 0) {
    lVar4 = *(long *)(param_1 + 0x48);
    if (lVar4 == 0) {
      func_0x010eec64(0);
    }
    uVar5 = *(undefined8 *)(lVar4 + 0x30);
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar2 = System_String__op_Inequality(0,uVar5,param_2,0);
    if ((uVar2 & 1) != 0) {
      lVar4 = *(long *)(param_1 + 0x48);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      Chat_Chatting__LeaveFromChannel(param_1,*(undefined8 *)(lVar4 + 0x30));
    }
  }
  if (*(long *)(param_1 + 0x38) != 0) {
    uVar5 = *(undefined8 *)(*(long *)(param_1 + 0x38) + 0x10);
    if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0x12a) & 1)
         != 0) &&
       (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar3 = CodeStage_AntiCheat_ObscuredTypes_ObscuredString__op_Implicit(0,param_2,0);
    uVar2 = CodeStage_AntiCheat_ObscuredTypes_ObscuredString__op_Inequality(0,uVar5,uVar3,0);
    if ((uVar2 & 1) != 0) {
      lVar4 = *(long *)(param_1 + 0x38);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      uVar5 = *(undefined8 *)(lVar4 + 0x10);
      if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0x12a) & 1)
           != 0) &&
         (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar5 = CodeStage_AntiCheat_ObscuredTypes_ObscuredString__op_Implicit(0,uVar5,0);
      Chat_Chatting__LeaveFromChannel(param_1,uVar5);
      lVar4 = *(long *)(param_1 + 0x30);
      uVar5 = *(undefined8 *)(param_1 + 0x38);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      System_Collections_Generic_List<tk2dUILayoutItem>__Remove
                (lVar4,uVar5,_mm_Method_System_Collections_Generic_List<Room>_Remove__);
    }
  }
  lVar4 = func_0x010fdda8(_class_Chat_Room_TypeInfo);
  Chat_Room___ctor();
  if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0x12a) & 1) !=
       0) && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0xd8) == 0
             )) {
    func_0x010da55c();
  }
  uVar5 = CodeStage_AntiCheat_ObscuredTypes_ObscuredString__op_Implicit(0,param_2,0);
  if (lVar4 == 0) {
    func_0x010eec64(0);
    uRam0000000000000010 = uVar5;
    func_0x010eec64(0);
  }
  else {
    *(undefined8 *)(lVar4 + 0x10) = uVar5;
  }
  *(undefined4 *)(lVar4 + 0x18) = 0;
  if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredInt_TypeInfo + 0x12a) & 1) != 0)
     && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredInt_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  auVar7 = CodeStage_AntiCheat_ObscuredTypes_ObscuredInt__op_Implicit(0,0,0);
  *(undefined1 (*) [12])(lVar4 + 0x1c) = auVar7;
  *(undefined4 *)(lVar4 + 0x28) = 0;
  *(undefined4 *)(lVar4 + 0x2c) = 1;
  lVar6 = *(long *)(param_1 + 0x30);
  *(long *)(param_1 + 0x38) = lVar4;
  if (lVar6 == 0) {
    func_0x010eec64(0);
  }
  System_Collections_Generic_List<tk2dUILayoutItem>__Insert
            (lVar6,0,lVar4,_mm_Method_System_Collections_Generic_List<Room>_Insert__);
  Chat_Chatting__JoinToChannel(param_1,param_2);
  lVar4 = *(long *)(param_1 + 0x48);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  *(undefined8 *)(lVar4 + 0x30) = param_2;
  return;
}


