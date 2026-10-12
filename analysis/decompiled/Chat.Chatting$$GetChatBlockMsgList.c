// name: Chat.Chatting$$GetChatBlockMsgList
// entry: 01d30048

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

long Chat_Chatting__GetChatBlockMsgList(long param_1,int param_2)

{
  bool bVar1;
  int iVar2;
  int iVar3;
  long lVar4;
  ulong uVar5;
  long *plVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  long lVar9;
  
  if ((bRam00000000049fcf4a & 1) == 0) {
    func_0x010c65d4(0x25c6);
    bRam00000000049fcf4a = 1;
  }
  lVar4 = func_0x010fdda8(_class_System_Collections_Generic_List<ChattingMessage>_TypeInfo);
  System_Collections_Generic_List<tk2dUILayoutItem>___ctor
            (lVar4,_mm_Method_System_Collections_Generic_List<ChattingMessage>__ctor__);
  lVar9 = *(long *)(param_1 + 0x70);
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  uVar5 = System_Collections_Generic_Dictionary<ListenerPrefix,_HttpListener>__ContainsKey
                    (lVar9,_str_all,
                     _mm_Method_System_Collections_Generic_Dictionary<string,_List<EventArgs>>_ContainsKey__
                    );
  uVar8 = _mm_Method_Chat_Chatting_<GetChatBlockMsgList>m__1__;
  if ((uVar5 & 1) != 0) {
    lVar9 = func_0x010fdda8(_class_System_Func<int,_EventArgs>_TypeInfo);
    System_Func<int,_object>___ctor
              (lVar9,param_1,uVar8,_mm_Method_System_Func<int,_EventArgs>__ctor__);
    iVar3 = param_2;
    if (-1 < param_2) {
      do {
        if (lVar9 == 0) {
          func_0x010eec64(0);
        }
        plVar6 = (long *)System_Func<int,_object>__Invoke
                                   (lVar9,iVar3,_mm_Method_System_Func<int,_EventArgs>_Invoke__);
        if (plVar6 != (long *)0x0) {
          if ((*(byte *)(_class_Chat_ChattingMessage_TypeInfo + 0x124) <= *(byte *)(*plVar6 + 0x124)
              ) && (*(long *)(*(long *)(*plVar6 + 200) +
                              (ulong)*(byte *)(_class_Chat_ChattingMessage_TypeInfo + 0x124) * 8 +
                             -8) == _class_Chat_ChattingMessage_TypeInfo)) {
            if (lVar4 == 0) {
              func_0x010eec64(0);
            }
            iVar2 = System_Collections_Generic_List<tk2dUILayoutItem>__get_Count
                              (lVar4,
                               _mm_Method_System_Collections_Generic_List<ChattingMessage>_get_Count__
                              );
            if (4 < iVar2) break;
            if (lVar4 == 0) {
              func_0x010eec64(0);
            }
            if (*(byte *)(*plVar6 + 0x124) < *(byte *)(_class_Chat_ChattingMessage_TypeInfo + 0x124)
               ) {
              plVar6 = (long *)0x0;
            }
            else if (*(long *)(*(long *)(*plVar6 + 200) +
                               (ulong)*(byte *)(_class_Chat_ChattingMessage_TypeInfo + 0x124) * 8 +
                              -8) != _class_Chat_ChattingMessage_TypeInfo) {
              plVar6 = (long *)0x0;
            }
            System_Collections_Generic_List<tk2dUILayoutItem>__Add
                      (lVar4,plVar6,
                       _mm_Method_System_Collections_Generic_List<ChattingMessage>_Add__);
          }
        }
        bVar1 = 0 < iVar3;
        iVar3 = iVar3 + -1;
      } while (bVar1);
    }
    param_2 = param_2 + 1;
    if (param_2 <= *(int *)(param_1 + 0x84)) {
      do {
        if (lVar9 == 0) {
          func_0x010eec64(0);
        }
        plVar6 = (long *)System_Func<int,_object>__Invoke
                                   (lVar9,param_2,_mm_Method_System_Func<int,_EventArgs>_Invoke__);
        if (plVar6 != (long *)0x0) {
          if ((*(byte *)(_class_Chat_ChattingMessage_TypeInfo + 0x124) <= *(byte *)(*plVar6 + 0x124)
              ) && (*(long *)(*(long *)(*plVar6 + 200) +
                              (ulong)*(byte *)(_class_Chat_ChattingMessage_TypeInfo + 0x124) * 8 +
                             -8) == _class_Chat_ChattingMessage_TypeInfo)) {
            if (lVar4 == 0) {
              func_0x010eec64(0);
            }
            iVar3 = System_Collections_Generic_List<tk2dUILayoutItem>__get_Count
                              (lVar4,
                               _mm_Method_System_Collections_Generic_List<ChattingMessage>_get_Count__
                              );
            if (9 < iVar3) break;
            if (lVar4 == 0) {
              func_0x010eec64(0);
            }
            if (*(byte *)(*plVar6 + 0x124) < *(byte *)(_class_Chat_ChattingMessage_TypeInfo + 0x124)
               ) {
              plVar6 = (long *)0x0;
            }
            else if (*(long *)(*(long *)(*plVar6 + 200) +
                               (ulong)*(byte *)(_class_Chat_ChattingMessage_TypeInfo + 0x124) * 8 +
                              -8) != _class_Chat_ChattingMessage_TypeInfo) {
              plVar6 = (long *)0x0;
            }
            System_Collections_Generic_List<tk2dUILayoutItem>__Add
                      (lVar4,plVar6,
                       _mm_Method_System_Collections_Generic_List<ChattingMessage>_Add__);
          }
        }
        param_2 = param_2 + 1;
      } while (param_2 <= *(int *)(param_1 + 0x84));
    }
    if (((*(byte *)(_class_Chat_Chatting_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Chat_Chatting_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar8 = _mm_Method_Chat_Chatting_<GetChatBlockMsgList>m__2__;
    if (*(long *)(*(long *)(_class_Chat_Chatting_TypeInfo + 0xb8) + 0x10) == 0) {
      uVar7 = func_0x010fdda8(_class_System_Comparison<ChattingMessage>_TypeInfo);
      System_Comparison<tk2dUICamera>___ctor
                (uVar7,0,uVar8,_mm_Method_System_Comparison<ChattingMessage>__ctor__);
      if (((*(byte *)(_class_Chat_Chatting_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Chat_Chatting_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      *(undefined8 *)(*(long *)(_class_Chat_Chatting_TypeInfo + 0xb8) + 0x10) = uVar7;
    }
    if (((*(byte *)(_class_Chat_Chatting_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Chat_Chatting_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar8 = *(undefined8 *)(*(long *)(_class_Chat_Chatting_TypeInfo + 0xb8) + 0x10);
    if (lVar4 == 0) {
      func_0x010eec64(0);
    }
    System_Collections_Generic_List<tk2dUICamera>__Sort
              (lVar4,uVar8,_mm_Method_System_Collections_Generic_List<ChattingMessage>_Sort__);
  }
  return lVar4;
}


