// name: Chat.Chatting$$LeaveFromChannel
// entry: 01d2bc74

/* WARNING: Removing unreachable block (ram,0x01d2be3c) */
/* WARNING: Removing unreachable block (ram,0x01d2be40) */
/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__LeaveFromChannel(long param_1,undefined8 param_2)

{
  uint uVar1;
  undefined8 uVar2;
  long lVar3;
  undefined8 uStack_50;
  undefined8 uStack_48;
  undefined8 uStack_40;
  long lStack_38;
  
  if ((bRam00000000049fcf1b & 1) == 0) {
    func_0x010c65d4(0x25d7);
    bRam00000000049fcf1b = 1;
  }
  uStack_40 = 0;
  lStack_38 = 0;
  uStack_50 = 0;
  uStack_48 = 0;
  if (*(char *)(param_1 + 0x80) != '\0') {
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar1 = System_String__IsNullOrEmpty(0,param_2,0);
    if ((uVar1 & 1) == 0) {
      lStack_38 = 0;
      lVar3 = *(long *)(param_1 + 0x68);
      if (lVar3 == 0) {
        func_0x010eec64(0);
      }
      System_Collections_Generic_Dictionary<UserUnit,_Dictionary<Type,_SpActionBase>>__TryGetValue
                (lVar3,param_2,&lStack_38,
                 _mm_Method_System_Collections_Generic_Dictionary<string,_HashSet<string>>_TryGetValue__
                );
      lVar3 = *(long *)(param_1 + 0x48);
      if (lVar3 == 0) {
        func_0x010eec64(0);
      }
      lVar3 = *(long *)(lVar3 + 0x28);
      if (lVar3 == 0) {
        func_0x010eec64(0);
      }
      System_Collections_Generic_List<tk2dUILayoutItem>__Remove
                (lVar3,param_2,_mm_Method_System_Collections_Generic_List<string>_Remove__);
      lVar3 = *(long *)(param_1 + 0x68);
      if (lVar3 == 0) {
        func_0x010eec64(0);
      }
      System_Collections_Generic_Dictionary<ListenerPrefix,_HttpListener>__Remove
                (lVar3,param_2,
                 _mm_Method_System_Collections_Generic_Dictionary<string,_HashSet<string>>_Remove__)
      ;
      Chat_Chatting__EmitLeaveRoom(param_1,param_2);
      Chat_Chatting__EmitBroadcast(param_1,0,*(undefined8 *)(param_1 + 0x48),param_2);
      if (lStack_38 != 0) {
        System_Collections_Generic_HashSet<object>__GetEnumerator
                  (&uStack_50,lStack_38,
                   _mm_Method_System_Collections_Generic_HashSet<string>_GetEnumerator__);
        while (uVar1 = func_0x02e777f4(&uStack_50,
                                       _mm_Method_System_Collections_Generic_HashSet_Enumerator<string>_MoveNext__
                                      ), (uVar1 & 1) != 0) {
          uVar2 = func_0x02e77924(&uStack_50,
                                  _mm_Method_System_Collections_Generic_HashSet_Enumerator<string>_get_Current__
                                 );
          Chat_Chatting__CleanChattingUser(param_1,uVar2);
        }
        func_0x02e77934(&uStack_50,
                        _mm_Method_System_Collections_Generic_HashSet_Enumerator<string>_Dispose__);
      }
    }
  }
  return;
}


