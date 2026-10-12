// name: Chat.Chatting$$LeaveFromMultiplayWatchChannel
// entry: 01d31ca8

/* WARNING: Removing unreachable block (ram,0x01d31e28) */
/* WARNING: Removing unreachable block (ram,0x01d31e2c) */
/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__LeaveFromMultiplayWatchChannel(long param_1)

{
  uint uVar1;
  long lVar2;
  undefined8 uVar3;
  undefined8 uVar4;
  undefined8 uStack_78;
  undefined8 uStack_70;
  undefined8 uStack_68;
  undefined8 uStack_60;
  undefined8 uStack_58;
  undefined8 uStack_50;
  
  if ((bRam00000000049fcf61 & 1) == 0) {
    func_0x010c65d4(0x25d8);
    bRam00000000049fcf61 = 1;
  }
  uStack_58 = 0;
  uStack_50 = 0;
  uStack_60 = 0;
  if (*(long *)(param_1 + 0x38) != 0) {
    uVar4 = *(undefined8 *)(*(long *)(param_1 + 0x38) + 0x10);
    if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0x12a) & 1)
         != 0) &&
       (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar4 = CodeStage_AntiCheat_ObscuredTypes_ObscuredString__op_Implicit(0,uVar4,0);
    Chat_Chatting__LeaveFromChannel(param_1,uVar4);
  }
  if (*(long *)(param_1 + 0x30) != 0) {
    System_Collections_Generic_List<object>__GetEnumerator
              (&uStack_78,*(long *)(param_1 + 0x30),
               _mm_Method_System_Collections_Generic_List<Room>_GetEnumerator__);
    uStack_50 = uStack_68;
    uStack_58 = uStack_70;
    uStack_60 = uStack_78;
    while (uVar1 = func_0x027e6418(&uStack_60,
                                   _mm_Method_System_Collections_Generic_List_Enumerator<Room>_MoveNext__
                                  ), (uVar1 & 1) != 0) {
      lVar2 = func_0x027e64f4(&uStack_60,
                              _mm_Method_System_Collections_Generic_List_Enumerator<Room>_get_Current__
                             );
      if (lVar2 == 0) {
        func_0x010eec64(0);
      }
      if (*(int *)(lVar2 + 0x2c) == 1) {
        uVar4 = *(undefined8 *)(lVar2 + 0x10);
        if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0x12a) &
             1) != 0) &&
           (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0xd8) == 0))
        {
          func_0x010da55c();
        }
        uVar4 = CodeStage_AntiCheat_ObscuredTypes_ObscuredString__op_Implicit(0,uVar4,0);
        Chat_Chatting__LeaveFromChannel(param_1,uVar4);
      }
    }
    func_0x027e6294(&uStack_60,_mm_Method_System_Collections_Generic_List_Enumerator<Room>_Dispose__
                   );
    lVar2 = *(long *)(param_1 + 0x30);
    if (((*(byte *)(_class_Chat_Chatting_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Chat_Chatting_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar4 = _mm_Method_Chat_Chatting_<LeaveFromMultiplayWatchChannel>m__6__;
    if (*(long *)(*(long *)(_class_Chat_Chatting_TypeInfo + 0xb8) + 0x28) == 0) {
      uVar3 = func_0x010fdda8(_class_System_Predicate<Room>_TypeInfo);
      System_Predicate<Action>___ctor(uVar3,0,uVar4,_mm_Method_System_Predicate<Room>__ctor__);
      if (((*(byte *)(_class_Chat_Chatting_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Chat_Chatting_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      *(undefined8 *)(*(long *)(_class_Chat_Chatting_TypeInfo + 0xb8) + 0x28) = uVar3;
    }
    if (((*(byte *)(_class_Chat_Chatting_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Chat_Chatting_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar4 = *(undefined8 *)(*(long *)(_class_Chat_Chatting_TypeInfo + 0xb8) + 0x28);
    if (lVar2 == 0) {
      func_0x010eec64(0);
    }
    System_Collections_Generic_List<Action>__RemoveAll
              (lVar2,uVar4,_mm_Method_System_Collections_Generic_List<Room>_RemoveAll__);
  }
  *(undefined8 *)(param_1 + 0x38) = 0;
  if (*(long *)(param_1 + 0x48) != 0) {
    *(undefined8 *)(*(long *)(param_1 + 0x48) + 0x30) = 0;
  }
  if (*(char *)(param_1 + 0x80) != '\0') {
    Chat_Chatting__GetRoomList(param_1);
  }
  return;
}


