// name: Chat.Chatting$$GetEmptyChannel
// entry: 01d2b604

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__GetEmptyChannel(long param_1)

{
  uint uVar1;
  undefined4 uVar2;
  int iVar3;
  int iVar4;
  int iVar5;
  ulong uVar6;
  long lVar7;
  undefined8 uVar8;
  ulong unaff_x22;
  ulong unaff_x23;
  undefined1 auVar9 [12];
  long lStack_68;
  
  if ((bRam00000000049fcf3c & 1) == 0) {
    func_0x010c65d4(0x25cb);
    bRam00000000049fcf3c = 1;
  }
  lVar7 = *(long *)(param_1 + 0x48);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  uVar8 = *(undefined8 *)(lVar7 + 0x30);
  if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar6 = System_String__IsNullOrEmpty(0,uVar8,0);
  if ((uVar6 & 1) == 0) {
    return;
  }
  lStack_68 = 0;
  iVar5 = 0;
  iVar4 = 0x7fffffff;
  while( true ) {
    lVar7 = *(long *)(param_1 + 0x30);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    iVar3 = System_Collections_Generic_List<tk2dUILayoutItem>__get_Count
                      (lVar7,_mm_Method_System_Collections_Generic_List<Room>_get_Count__);
    if (iVar3 <= iVar5) break;
    lVar7 = *(long *)(param_1 + 0x30);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    lVar7 = System_Collections_Generic_List<object>__get_Item
                      (lVar7,iVar5,_mm_Method_System_Collections_Generic_List<Room>_get_Item__);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    uVar8 = *(undefined8 *)(lVar7 + 0x1c);
    uVar1 = *(uint *)(lVar7 + 0x24);
    if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredInt_TypeInfo + 0x12a) & 1) != 0
        ) && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredInt_TypeInfo + 0xd8) == 0))
    {
      func_0x010da55c();
    }
    unaff_x23 = (ulong)uVar1 | unaff_x23 & 0xffffffff00000000;
    iVar3 = CodeStage_AntiCheat_ObscuredTypes_ObscuredInt__op_Implicit(0,uVar8,unaff_x23,0);
    if (iVar3 <= iVar4) {
      if (lVar7 == 0) {
        func_0x010eec64(0);
      }
      if (*(int *)(lVar7 + 0x28) != 2) {
        uVar8 = *(undefined8 *)(lVar7 + 0x1c);
        uVar1 = *(uint *)(lVar7 + 0x24);
        if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredInt_TypeInfo + 0x12a) & 1)
             != 0) &&
           (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredInt_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        unaff_x22 = (ulong)uVar1 | unaff_x22 & 0xffffffff00000000;
        iVar4 = CodeStage_AntiCheat_ObscuredTypes_ObscuredInt__op_Implicit(0,uVar8,unaff_x22,0);
        lStack_68 = lVar7;
      }
    }
    iVar5 = iVar5 + 1;
  }
  if (lStack_68 != 0) {
    uVar8 = *(undefined8 *)(lStack_68 + 0x1c);
    uVar2 = *(undefined4 *)(lStack_68 + 0x24);
    if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredInt_TypeInfo + 0x12a) & 1) != 0
        ) && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredInt_TypeInfo + 0xd8) == 0))
    {
      func_0x010da55c();
    }
    iVar5 = CodeStage_AntiCheat_ObscuredTypes_ObscuredInt__op_Implicit(0,uVar8,uVar2,0);
    if (iVar5 < 4) goto code_r0x01d2b8e0;
  }
  lStack_68 = func_0x010fdda8(_class_Chat_Room_TypeInfo);
  Chat_Room___ctor();
  uVar8 = Chat_Chatting__GetChannelRoomId(param_1,*(undefined8 *)(param_1 + 0x30));
  if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0x12a) & 1) !=
       0) && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0xd8) == 0
             )) {
    func_0x010da55c(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo);
  }
  uVar8 = CodeStage_AntiCheat_ObscuredTypes_ObscuredString__op_Implicit(0,uVar8,0);
  if (lStack_68 == 0) {
    func_0x010eec64(0);
  }
  *(undefined8 *)(lStack_68 + 0x10) = uVar8;
  if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredInt_TypeInfo + 0x12a) & 1) != 0)
     && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredInt_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  auVar9 = CodeStage_AntiCheat_ObscuredTypes_ObscuredInt__op_Implicit(0,0,0);
  *(undefined1 (*) [12])(lStack_68 + 0x1c) = auVar9;
  lVar7 = *(long *)(param_1 + 0x30);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  System_Collections_Generic_List<tk2dUILayoutItem>__Add
            (lVar7,lStack_68,_mm_Method_System_Collections_Generic_List<Room>_Add__);
code_r0x01d2b8e0:
  lVar7 = *(long *)(param_1 + 0x48);
  if (lStack_68 == 0) {
    func_0x010eec64(0);
  }
  uVar8 = *(undefined8 *)(lStack_68 + 0x10);
  if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0x12a) & 1) !=
       0) && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0xd8) == 0
             )) {
    func_0x010da55c();
  }
  uVar8 = CodeStage_AntiCheat_ObscuredTypes_ObscuredString__op_Implicit(0,uVar8,0);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  *(undefined8 *)(lVar7 + 0x30) = uVar8;
  return;
}


