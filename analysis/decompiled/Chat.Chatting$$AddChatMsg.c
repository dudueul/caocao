// name: Chat.Chatting$$AddChatMsg
// entry: 01d2e504

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__AddChatMsg(long param_1,undefined8 param_2,undefined8 param_3,long param_4)

{
  long lVar1;
  uint uVar2;
  int iVar3;
  undefined8 uVar4;
  ulong uVar5;
  long lVar6;
  ulong uVar7;
  undefined1 auVar8 [16];
  
  if ((bRam00000000049fcf42 & 1) == 0) {
    func_0x010c65d4(0x25ab);
    bRam00000000049fcf42 = 1;
  }
  uVar4 = Chat_Chatting__GetDestRoomId(param_1,param_2);
  if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_string_TypeInfo);
  }
  uVar2 = System_String__IsNullOrEmpty(0,uVar4,0);
  if ((uVar2 & 1) != 0) {
    return;
  }
  Chat_Chatting__AddChat(param_1,_str_all,param_2);
  Chat_Chatting__TrimChat(param_1,_str_all);
  Chat_Chatting__AddChat(param_1,uVar4,param_2);
  if ((bRam00000000049fcf44 & 1) == 0) {
    func_0x010c65d4(0x25f7);
    bRam00000000049fcf44 = 1;
  }
  lVar6 = *(long *)(param_1 + 0x70);
  if (lVar6 == 0) {
    func_0x010eec64(0);
  }
  uVar5 = System_Collections_Generic_Dictionary<ListenerPrefix,_HttpListener>__ContainsKey
                    (lVar6,uVar4,
                     _mm_Method_System_Collections_Generic_Dictionary<string,_List<EventArgs>>_ContainsKey__
                    );
  if ((uVar5 & 1) != 0) {
    lVar6 = *(long *)(param_1 + 0x70);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    lVar6 = System_Collections_Generic_Dictionary<object,_object>__get_Item
                      (lVar6,uVar4,
                       _mm_Method_System_Collections_Generic_Dictionary<string,_List<EventArgs>>_get_Item__
                      );
    if ((lVar6 != 0) &&
       (iVar3 = System_Collections_Generic_List<tk2dUILayoutItem>__get_Count
                          (lVar6,_mm_Method_System_Collections_Generic_List<EventArgs>_get_Count__),
       lVar1 = _mm_Method_System_Collections_Generic_List<EventArgs>_RemoveAt__, 199 < iVar3)) {
      if ((bRam0000000004a09250 & 1) == 0) {
        func_0x010c65d4(0x7e85);
        bRam0000000004a09250 = 1;
      }
      if (*(int *)(lVar6 + 0x18) == 0) {
        uVar4 = func_0x010fdda8(_class_System_ArgumentOutOfRangeException_TypeInfo);
        System_ArgumentOutOfRangeException___ctor(uVar4,_str_index,0);
        uVar5 = _mm_Method_System_Collections_Generic_List<object>_RemoveAt__;
        func_0x010eebec(uVar4,0);
        auVar8 = func_0x0112148c();
        lVar6 = auVar8._0_8_;
        uVar7 = auVar8._8_8_ & 0xffffffff;
        if (lVar6 == 0) {
          func_0x010eec64(0);
        }
        (*(code *)**(undefined8 **)(*(long *)(*(long *)(param_4 + 0x18) + 0xc0) + 0x118))
                  (lVar6,uVar7,uVar5 & 0xffffffff);
        if (0 < (int)uVar5) {
          if (lVar6 == 0) {
            func_0x010eec64(0);
          }
          (*(code *)**(undefined8 **)(*(long *)(*(long *)(param_4 + 0x18) + 0xc0) + 0x120))
                    (lVar6,uVar7,-(int)uVar5);
          System_Array__Clear(0,*(undefined8 *)(lVar6 + 0x10),*(undefined4 *)(lVar6 + 0x18),
                              uVar5 & 0xffffffff,0);
          *(int *)(lVar6 + 0x1c) = *(int *)(lVar6 + 0x1c) + 1;
        }
        return;
      }
      (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar1 + 0x18) + 0xc0) + 0x120))
                (lVar6,0,0xffffffff);
      System_Array__Clear(0,*(undefined8 *)(lVar6 + 0x10),*(undefined4 *)(lVar6 + 0x18),1,0);
      *(int *)(lVar6 + 0x1c) = *(int *)(lVar6 + 0x1c) + 1;
      return;
    }
  }
  return;
}


