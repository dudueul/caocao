// name: Chat.Chatting$$RegisterFriends
// entry: 01d2ab94

/* WARNING: Removing unreachable block (ram,0x01d2adec) */
/* WARNING: Removing unreachable block (ram,0x01d2adf0) */
/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__RegisterFriends(long param_1,undefined8 param_2,long param_3)

{
  uint uVar1;
  long lVar2;
  long lVar3;
  long lVar4;
  ulong uVar5;
  long lVar6;
  undefined8 uVar7;
  undefined8 uStack_98;
  undefined8 uStack_90;
  undefined8 uStack_88;
  undefined8 uStack_80;
  undefined8 uStack_78;
  undefined8 uStack_70;
  
  if ((bRam00000000049fcf18 & 1) == 0) {
    func_0x010c65d4(0x25f0);
    bRam00000000049fcf18 = 1;
  }
  uStack_78 = 0;
  uStack_70 = 0;
  uStack_80 = 0;
  lVar2 = func_0x010fdda8(_class_System_Collections_Generic_List<string>_TypeInfo);
  System_Collections_Generic_List<tk2dUILayoutItem>___ctor
            (lVar2,_mm_Method_System_Collections_Generic_List<string>__ctor__);
  lVar3 = func_0x010fdda8(_class_Chat_ChattingUserRequest_TypeInfo);
  System_Object___ctor(lVar3,0);
  *(undefined8 *)(lVar3 + 0x10) = param_2;
  if (param_3 == 0) {
    func_0x010eec64(0);
  }
  System_Collections_Generic_List<object>__GetEnumerator
            (&uStack_98,param_3,
             _mm_Method_System_Collections_Generic_List<ChattingUser>_GetEnumerator__);
  uStack_70 = uStack_88;
  uStack_78 = uStack_90;
  uStack_80 = uStack_98;
  while (uVar1 = func_0x027e6418(&uStack_80,
                                 _mm_Method_System_Collections_Generic_List_Enumerator<ChattingUser>_MoveNext__
                                ), (uVar1 & 1) != 0) {
    lVar4 = func_0x027e64f4(&uStack_80,
                            _mm_Method_System_Collections_Generic_List_Enumerator<ChattingUser>_get_Current__
                           );
    lVar6 = *(long *)(param_1 + 0x58);
    if (lVar4 == 0) {
      func_0x010eec64(0);
    }
    uVar7 = *(undefined8 *)(lVar4 + 0x10);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    uVar5 = System_Collections_Generic_HashSet<UserUnit>__Contains
                      (lVar6,uVar7,_mm_Method_System_Collections_Generic_HashSet<string>_Contains__)
    ;
    if ((uVar5 & 1) == 0) {
      if (lVar4 == 0) {
        uVar5 = func_0x010eec64(0);
      }
      uVar7 = Chat_Chatting__GetPubRoomId(uVar5,*(undefined8 *)(lVar4 + 0x18));
      if (lVar2 == 0) {
        func_0x010eec64(0);
      }
      System_Collections_Generic_List<tk2dUILayoutItem>__Add
                (lVar2,uVar7,_mm_Method_System_Collections_Generic_List<string>_Add__);
      lVar6 = *(long *)(param_1 + 0x58);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      uVar7 = *(undefined8 *)(lVar4 + 0x10);
      if (lVar6 == 0) {
        func_0x010eec64(0);
      }
      System_Collections_Generic_HashSet<UserUnit>__Add
                (lVar6,uVar7,_mm_Method_System_Collections_Generic_HashSet<string>_Add__);
      lVar6 = *(long *)(param_1 + 0x60);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      uVar7 = *(undefined8 *)(lVar4 + 0x10);
      if (lVar6 == 0) {
        func_0x010eec64(0);
      }
      uVar1 = System_Collections_Generic_Dictionary<ListenerPrefix,_HttpListener>__ContainsKey
                        (lVar6,uVar7,
                         _mm_Method_System_Collections_Generic_Dictionary<string,_ChattingUser>_ContainsKey__
                        );
      if ((uVar1 & 1) == 0) {
        uVar7 = Chat_Chatting__OnChattingUser(param_1,lVar4);
        if (lVar4 == 0) {
          uVar7 = func_0x010eec64(0);
        }
        uVar7 = Chat_Chatting__GetSubRoomId(uVar7,*(undefined8 *)(lVar4 + 0x18));
        Chat_Chatting__EmitBroadcast(param_1,2,lVar3,uVar7);
      }
    }
  }
  func_0x027e6294(&uStack_80,
                  _mm_Method_System_Collections_Generic_List_Enumerator<ChattingUser>_Dispose__);
  Chat_Chatting__EmitRegisterSubscribingRooms(param_1,lVar2);
  return;
}


