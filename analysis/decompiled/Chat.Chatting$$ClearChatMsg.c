// name: Chat.Chatting$$ClearChatMsg
// entry: 01d2fc20

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__ClearChatMsg(undefined8 param_1,int param_2)

{
  int iVar1;
  undefined8 uVar2;
  long lVar3;
  long *plVar4;
  
  if ((bRam00000000049fcf45 & 1) == 0) {
    func_0x010c65d4(0x25b8);
    bRam00000000049fcf45 = 1;
  }
  uVar2 = Chat_Chatting__GetDestRoomId(param_1,param_2);
  lVar3 = Chat_Chatting__GetChatMsgs(param_1,uVar2);
  if (lVar3 != 0) {
    System_Collections_Generic_List<tk2dUICamera>__Clear
              (lVar3,_mm_Method_System_Collections_Generic_List<EventArgs>_Clear__);
    lVar3 = Chat_Chatting__GetChatMsgs(param_1,_str_all);
    if ((lVar3 != 0) &&
       (iVar1 = System_Collections_Generic_List<tk2dUILayoutItem>__get_Count
                          (lVar3,_mm_Method_System_Collections_Generic_List<EventArgs>_get_Count__),
       0 < iVar1)) {
      iVar1 = System_Collections_Generic_List<tk2dUILayoutItem>__get_Count
                        (lVar3,_mm_Method_System_Collections_Generic_List<EventArgs>_get_Count__);
      while (iVar1 = iVar1 + -1, -1 < iVar1) {
        plVar4 = (long *)System_Collections_Generic_List<object>__get_Item
                                   (lVar3,iVar1,
                                    _mm_Method_System_Collections_Generic_List<EventArgs>_get_Item__
                                   );
        if (plVar4 != (long *)0x0) {
          if (((*(byte *)(_class_Chat_ChattingMessage_TypeInfo + 0x124) <=
                *(byte *)(*plVar4 + 0x124)) &&
              (*(long *)(*(long *)(*plVar4 + 200) +
                         (ulong)*(byte *)(_class_Chat_ChattingMessage_TypeInfo + 0x124) * 8 + -8) ==
               _class_Chat_ChattingMessage_TypeInfo)) && (*(int *)((long)plVar4 + 0x34) == param_2))
          {
            System_Collections_Generic_List<tk2dUIItem>__RemoveAt
                      (lVar3,iVar1,_mm_Method_System_Collections_Generic_List<EventArgs>_RemoveAt__)
            ;
          }
        }
      }
    }
  }
  return;
}


