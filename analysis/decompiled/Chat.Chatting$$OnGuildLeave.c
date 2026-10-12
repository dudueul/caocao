// name: Chat.Chatting$$OnGuildLeave
// entry: 01d31620

/* WARNING: Removing unreachable block (ram,0x01d2be3c) */
/* WARNING: Removing unreachable block (ram,0x01d2be40) */
/* WARNING: Removing unreachable block (ram,0x01d2bdb4) */
/* WARNING: Removing unreachable block (ram,0x01d2bdd8) */
/* WARNING: Removing unreachable block (ram,0x01d2bdec) */
/* WARNING: Removing unreachable block (ram,0x01d2be1c) */
/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__OnGuildLeave(long param_1,undefined8 param_2)

{
  uint uVar1;
  ulong uVar2;
  undefined8 uVar3;
  long lVar4;
  undefined8 uVar5;
  
  if ((bRam00000000049fcf5e & 1) == 0) {
    func_0x010c65d4(0x25eb);
    bRam00000000049fcf5e = 1;
  }
  if (((*(byte *)(_class_HttpRequest_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_HttpRequest_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar2 = HttpRequest__CommonCheck(0,param_2,0);
  if (((uVar2 & 1) != 0) && (*(long *)(param_1 + 0x48) != 0)) {
    lVar4 = *(long *)(*(long *)(param_1 + 0x48) + 0x28);
    if (((*(byte *)(_class_Chat_Chatting_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Chat_Chatting_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar5 = _mm_Method_Chat_Chatting_<OnGuildLeave>m__4__;
    if (*(long *)(*(long *)(_class_Chat_Chatting_TypeInfo + 0xb8) + 0x18) == 0) {
      uVar3 = func_0x010fdda8(_class_System_Predicate<string>_TypeInfo);
      System_Predicate<Action>___ctor(uVar3,0,uVar5,_mm_Method_System_Predicate<string>__ctor__);
      if (((*(byte *)(_class_Chat_Chatting_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Chat_Chatting_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      *(undefined8 *)(*(long *)(_class_Chat_Chatting_TypeInfo + 0xb8) + 0x18) = uVar3;
    }
    if (((*(byte *)(_class_Chat_Chatting_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Chat_Chatting_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar5 = *(undefined8 *)(*(long *)(_class_Chat_Chatting_TypeInfo + 0xb8) + 0x18);
    if (lVar4 == 0) {
      func_0x010eec64(0);
    }
    uVar5 = System_Collections_Generic_List<object>__Find
                      (lVar4,uVar5,_mm_Method_System_Collections_Generic_List<string>_Find__);
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_string_TypeInfo);
    }
    uVar1 = System_String__IsNullOrEmpty(0,uVar5,0);
    if ((uVar1 & 1) == 0) {
      if ((bRam00000000049fcf1b & 1) == 0) {
        func_0x010c65d4(0x25d7);
        bRam00000000049fcf1b = 1;
      }
      if (*(char *)(param_1 + 0x80) != '\0') {
        if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        uVar1 = System_String__IsNullOrEmpty(0,uVar5,0);
        if ((uVar1 & 1) == 0) {
          lVar4 = *(long *)(param_1 + 0x68);
          if (lVar4 == 0) {
            func_0x010eec64(0);
          }
          System_Collections_Generic_Dictionary<UserUnit,_Dictionary<Type,_SpActionBase>>__TryGetValue
                    (lVar4,uVar5,&stack0xffffffffffffffc8,
                     _mm_Method_System_Collections_Generic_Dictionary<string,_HashSet<string>>_TryGetValue__
                    );
          lVar4 = *(long *)(param_1 + 0x48);
          if (lVar4 == 0) {
            func_0x010eec64(0);
          }
          lVar4 = *(long *)(lVar4 + 0x28);
          if (lVar4 == 0) {
            func_0x010eec64(0);
          }
          System_Collections_Generic_List<tk2dUILayoutItem>__Remove
                    (lVar4,uVar5,_mm_Method_System_Collections_Generic_List<string>_Remove__);
          lVar4 = *(long *)(param_1 + 0x68);
          if (lVar4 == 0) {
            func_0x010eec64(0);
          }
          System_Collections_Generic_Dictionary<ListenerPrefix,_HttpListener>__Remove
                    (lVar4,uVar5,
                     _mm_Method_System_Collections_Generic_Dictionary<string,_HashSet<string>>_Remove__
                    );
          Chat_Chatting__EmitLeaveRoom(param_1,uVar5);
          Chat_Chatting__EmitBroadcast(param_1,0,*(undefined8 *)(param_1 + 0x48),uVar5);
        }
      }
      return;
    }
  }
  return;
}


