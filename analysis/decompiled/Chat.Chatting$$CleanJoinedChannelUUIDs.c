// name: Chat.Chatting$$CleanJoinedChannelUUIDs
// entry: 01d2e864

/* WARNING: Removing unreachable block (ram,0x01d2ec68) */
/* WARNING: Removing unreachable block (ram,0x01d2ec6c) */
/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__CleanJoinedChannelUUIDs(long param_1,long param_2)

{
  uint uVar1;
  undefined8 uVar2;
  long lVar3;
  long lVar4;
  undefined8 uVar5;
  undefined1 auVar6 [16];
  undefined8 uStack_d0;
  undefined8 uStack_c8;
  undefined8 uStack_c0;
  undefined8 uStack_b8;
  undefined8 uStack_b0;
  undefined8 uStack_a8;
  undefined8 uStack_a0;
  undefined8 uStack_98;
  undefined1 auStack_90 [16];
  undefined8 uStack_80;
  undefined8 uStack_78;
  undefined8 uStack_70;
  
  if ((bRam00000000049fcf3d & 1) == 0) {
    func_0x010c65d4(0x25b7);
    bRam00000000049fcf3d = 1;
  }
  uStack_78 = 0;
  uStack_70 = 0;
  auStack_90._8_8_ = 0;
  uStack_80 = 0;
  uStack_98 = 0;
  auStack_90._0_8_ = 0;
  uStack_a8 = 0;
  uStack_a0 = 0;
  uStack_b0 = 0;
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  lVar3 = *(long *)(param_2 + 0x28);
  if (lVar3 == 0) {
    func_0x010eec64(0);
  }
  System_Collections_Generic_List<object>__GetEnumerator
            (&uStack_d0,lVar3,_mm_Method_System_Collections_Generic_List<string>_GetEnumerator__);
  uStack_70 = uStack_c0;
  uStack_78 = uStack_c8;
  uStack_80 = uStack_d0;
  while (uVar1 = func_0x027e6418(&uStack_80,
                                 _mm_Method_System_Collections_Generic_List_Enumerator<string>_MoveNext__
                                ), (uVar1 & 1) != 0) {
    uVar2 = func_0x027e64f4(&uStack_80,
                            _mm_Method_System_Collections_Generic_List_Enumerator<string>_get_Current__
                           );
    lVar4 = *(long *)(param_1 + 0x68);
    if (lVar4 == 0) {
      func_0x010eec64(0);
    }
    uVar1 = System_Collections_Generic_Dictionary<ListenerPrefix,_HttpListener>__ContainsKey
                      (lVar4,uVar2,
                       _mm_Method_System_Collections_Generic_Dictionary<string,_HashSet<string>>_ContainsKey__
                      );
    if ((uVar1 & 1) != 0) {
      lVar4 = *(long *)(param_1 + 0x68);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      lVar4 = System_Collections_Generic_Dictionary<object,_object>__get_Item
                        (lVar4,uVar2,
                         _mm_Method_System_Collections_Generic_Dictionary<string,_HashSet<string>>_get_Item__
                        );
      if (param_2 == 0) {
        func_0x010eec64(0);
      }
      uVar5 = *(undefined8 *)(param_2 + 0x10);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      uVar1 = System_Collections_Generic_HashSet<UserUnit>__Contains
                        (lVar4,uVar5,
                         _mm_Method_System_Collections_Generic_HashSet<string>_Contains__);
      if ((uVar1 & 1) == 0) {
        if (param_2 == 0) {
          func_0x010eec64(0);
        }
        uVar5 = *(undefined8 *)(param_2 + 0x10);
        if (lVar4 == 0) {
          func_0x010eec64(0);
        }
        System_Collections_Generic_HashSet<UserUnit>__Add
                  (lVar4,uVar5,_mm_Method_System_Collections_Generic_HashSet<string>_Add__);
        lVar4 = *(long *)(param_1 + 0xf0);
        if (lVar4 != 0) {
          uVar5 = func_0x010fdda8(_class_Chat_ChattingUserJoinToChannel_TypeInfo);
          Chat_ChattingUserJoinToChannel___ctor(uVar5,param_2,uVar2);
          System_EventHandler<object>__Invoke
                    (lVar4,param_1,uVar5,
                     _mm_Method_System_EventHandler<ChattingUserJoinToChannel>_Invoke__);
        }
      }
    }
  }
  func_0x027e6294(&uStack_80,_mm_Method_System_Collections_Generic_List_Enumerator<string>_Dispose__
                 );
  lVar4 = *(long *)(param_1 + 0x68);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  System_Collections_Generic_Dictionary<object,_object>__GetEnumerator
            (&uStack_d0,lVar4,
             _mm_Method_System_Collections_Generic_Dictionary<string,_HashSet<string>>_GetEnumerator__
            );
  uStack_a8 = uStack_c8;
  uStack_b0 = uStack_d0;
  uStack_98 = uStack_b8;
  uStack_a0 = uStack_c0;
  while (uVar1 = func_0x01e14068(&uStack_b0,
                                 _mm_Method_System_Collections_Generic_Dictionary_Enumerator<string,_HashSet<string>>_MoveNext__
                                ), (uVar1 & 1) != 0) {
    auVar6 = func_0x01e1420c(&uStack_b0,
                             _mm_Method_System_Collections_Generic_Dictionary_Enumerator<string,_HashSet<string>>_get_Current__
                            );
    auStack_90 = auVar6;
    lVar4 = func_0x025ae750(auStack_90,
                            _mm_Method_System_Collections_Generic_KeyValuePair<string,_HashSet<string>>_get_Value__
                           );
    if (param_2 == 0) {
      func_0x010eec64(0);
    }
    uVar2 = *(undefined8 *)(param_2 + 0x10);
    if (lVar4 == 0) {
      func_0x010eec64(0);
    }
    uVar1 = System_Collections_Generic_HashSet<UserUnit>__Contains
                      (lVar4,uVar2,_mm_Method_System_Collections_Generic_HashSet<string>_Contains__)
    ;
    if ((uVar1 & 1) != 0) {
      uVar2 = func_0x025ae738(auStack_90,
                              _mm_Method_System_Collections_Generic_KeyValuePair<string,_HashSet<string>>_get_Key__
                             );
      if (lVar3 == 0) {
        func_0x010eec64(0);
      }
      uVar1 = System_Collections_Generic_List<Ai>__Contains
                        (lVar3,uVar2,_mm_Method_System_Collections_Generic_List<string>_Contains__);
      if ((uVar1 & 1) == 0) {
        if (param_2 == 0) {
          func_0x010eec64(0);
        }
        uVar2 = *(undefined8 *)(param_2 + 0x10);
        if (lVar4 == 0) {
          func_0x010eec64(0);
        }
        System_Collections_Generic_HashSet<Text>__Remove
                  (lVar4,uVar2,_mm_Method_System_Collections_Generic_HashSet<string>_Remove__);
        lVar4 = *(long *)(param_1 + 0xf8);
        if (lVar4 != 0) {
          uVar2 = func_0x025ae738(auStack_90,
                                  _mm_Method_System_Collections_Generic_KeyValuePair<string,_HashSet<string>>_get_Key__
                                 );
          uVar5 = func_0x010fdda8(_class_Chat_ChattingUserLeaveFromChannel_TypeInfo);
          Chat_ChattingUserLeaveFromChannel___ctor(uVar5,param_2,uVar2);
          System_EventHandler<object>__Invoke
                    (lVar4,param_1,uVar5,
                     _mm_Method_System_EventHandler<ChattingUserLeaveFromChannel>_Invoke__);
        }
      }
    }
  }
  func_0x01e144dc(&uStack_b0,
                  _mm_Method_System_Collections_Generic_Dictionary_Enumerator<string,_HashSet<string>>_Dispose__
                 );
  return;
}


