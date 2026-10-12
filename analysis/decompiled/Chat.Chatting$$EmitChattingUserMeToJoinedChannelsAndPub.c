// name: Chat.Chatting$$EmitChattingUserMeToJoinedChannelsAndPub
// entry: 01d29864

/* WARNING: Removing unreachable block (ram,0x01d29964) */
/* WARNING: Removing unreachable block (ram,0x01d29968) */
/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__EmitChattingUserMeToJoinedChannelsAndPub(long param_1)

{
  uint uVar1;
  undefined8 uVar2;
  long lVar3;
  undefined8 uStack_48;
  undefined8 uStack_40;
  undefined8 uStack_38;
  
  if ((bRam00000000049fcf29 & 1) == 0) {
    func_0x010c65d4(0x25bd);
    bRam00000000049fcf29 = 1;
  }
  uStack_40 = 0;
  uStack_38 = 0;
  uStack_48 = 0;
  if (*(char *)(param_1 + 0x80) != '\0') {
    lVar3 = *(long *)(param_1 + 0x48);
    if (lVar3 == 0) {
      func_0x010eec64(0);
    }
    lVar3 = *(long *)(lVar3 + 0x28);
    if (lVar3 == 0) {
      func_0x010eec64(0);
    }
    System_Collections_Generic_List<object>__GetEnumerator
              (&uStack_48,lVar3,_mm_Method_System_Collections_Generic_List<string>_GetEnumerator__);
    while (uVar1 = func_0x027e6418(&uStack_48,
                                   _mm_Method_System_Collections_Generic_List_Enumerator<string>_MoveNext__
                                  ), (uVar1 & 1) != 0) {
      uVar2 = func_0x027e64f4(&uStack_48,
                              _mm_Method_System_Collections_Generic_List_Enumerator<string>_get_Current__
                             );
      Chat_Chatting__EmitBroadcast(param_1,0,*(undefined8 *)(param_1 + 0x48),uVar2);
    }
    uVar2 = func_0x027e6294(&uStack_48,
                            _mm_Method_System_Collections_Generic_List_Enumerator<string>_Dispose__)
    ;
    lVar3 = *(long *)(param_1 + 0x48);
    if (lVar3 == 0) {
      uVar2 = func_0x010eec64(0);
    }
    uVar2 = Chat_Chatting__GetPubRoomId(uVar2,*(undefined8 *)(lVar3 + 0x18));
    Chat_Chatting__EmitBroadcast(param_1,0,lVar3,uVar2);
  }
  return;
}


