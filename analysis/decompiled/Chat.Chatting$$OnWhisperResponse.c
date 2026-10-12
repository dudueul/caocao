// name: Chat.Chatting$$OnWhisperResponse
// entry: 01d2e088

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__OnWhisperResponse(long param_1,long param_2,undefined8 param_3,long param_4)

{
  undefined4 uVar1;
  long lVar2;
  uint uVar3;
  int iVar4;
  undefined8 uVar5;
  ulong uVar6;
  long lVar7;
  ulong uVar8;
  undefined1 auVar9 [16];
  
  if ((bRam00000000049fcf32 & 1) == 0) {
    func_0x010c65d4(0x25ee);
    bRam00000000049fcf32 = 1;
  }
  if (param_2 == 0) {
    return;
  }
  Chat_WhisperResponse__DeserializeSubType(param_2);
  if (*(long *)(param_1 + 0xd0) != 0) {
    param_4 = _mm_Method_System_EventHandler<WhisperResponse>_Invoke__;
    System_EventHandler<object>__Invoke(*(long *)(param_1 + 0xd0),param_1,param_2);
  }
  lVar7 = *(long *)(param_1 + 0x90);
  uVar1 = *(undefined4 *)(param_2 + 0x28);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  System_Collections_Generic_List<int>__Remove
            (lVar7,uVar1,_mm_Method_System_Collections_Generic_List<int>_Remove__);
  if ((bRam00000000049fcf42 & 1) == 0) {
    func_0x010c65d4(0x25ab);
    bRam00000000049fcf42 = 1;
  }
  uVar5 = Chat_Chatting__GetDestRoomId(param_1,param_2);
  if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_string_TypeInfo);
  }
  uVar3 = System_String__IsNullOrEmpty(0,uVar5,0);
  if ((uVar3 & 1) == 0) {
    Chat_Chatting__AddChat(param_1,_str_all,param_2);
    Chat_Chatting__TrimChat(param_1,_str_all);
    Chat_Chatting__AddChat(param_1,uVar5,param_2);
    if ((bRam00000000049fcf44 & 1) == 0) {
      func_0x010c65d4(0x25f7);
      bRam00000000049fcf44 = 1;
    }
    lVar7 = *(long *)(param_1 + 0x70);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    uVar6 = System_Collections_Generic_Dictionary<ListenerPrefix,_HttpListener>__ContainsKey
                      (lVar7,uVar5,
                       _mm_Method_System_Collections_Generic_Dictionary<string,_List<EventArgs>>_ContainsKey__
                      );
    if ((uVar6 & 1) != 0) {
      lVar7 = *(long *)(param_1 + 0x70);
      if (lVar7 == 0) {
        func_0x010eec64(0);
      }
      lVar7 = System_Collections_Generic_Dictionary<object,_object>__get_Item
                        (lVar7,uVar5,
                         _mm_Method_System_Collections_Generic_Dictionary<string,_List<EventArgs>>_get_Item__
                        );
      if ((lVar7 != 0) &&
         (iVar4 = System_Collections_Generic_List<tk2dUILayoutItem>__get_Count
                            (lVar7,_mm_Method_System_Collections_Generic_List<EventArgs>_get_Count__
                            ),
         lVar2 = _mm_Method_System_Collections_Generic_List<EventArgs>_RemoveAt__, 199 < iVar4)) {
        if ((bRam0000000004a09250 & 1) == 0) {
          func_0x010c65d4(0x7e85);
          bRam0000000004a09250 = 1;
        }
        if (*(int *)(lVar7 + 0x18) == 0) {
          uVar5 = func_0x010fdda8(_class_System_ArgumentOutOfRangeException_TypeInfo);
          System_ArgumentOutOfRangeException___ctor(uVar5,_str_index,0);
          uVar6 = _mm_Method_System_Collections_Generic_List<object>_RemoveAt__;
          func_0x010eebec(uVar5,0);
          auVar9 = func_0x0112148c();
          lVar7 = auVar9._0_8_;
          uVar8 = auVar9._8_8_ & 0xffffffff;
          if (lVar7 == 0) {
            func_0x010eec64(0);
          }
          (*(code *)**(undefined8 **)(*(long *)(*(long *)(param_4 + 0x18) + 0xc0) + 0x118))
                    (lVar7,uVar8,uVar6 & 0xffffffff);
          if (0 < (int)uVar6) {
            if (lVar7 == 0) {
              func_0x010eec64(0);
            }
            (*(code *)**(undefined8 **)(*(long *)(*(long *)(param_4 + 0x18) + 0xc0) + 0x120))
                      (lVar7,uVar8,-(int)uVar6);
            System_Array__Clear(0,*(undefined8 *)(lVar7 + 0x10),*(undefined4 *)(lVar7 + 0x18),
                                uVar6 & 0xffffffff,0);
            *(int *)(lVar7 + 0x1c) = *(int *)(lVar7 + 0x1c) + 1;
          }
          return;
        }
        (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar2 + 0x18) + 0xc0) + 0x120))
                  (lVar7,0,0xffffffff);
        System_Array__Clear(0,*(undefined8 *)(lVar7 + 0x10),*(undefined4 *)(lVar7 + 0x18),1,0);
        *(int *)(lVar7 + 0x1c) = *(int *)(lVar7 + 0x1c) + 1;
        return;
      }
    }
    return;
  }
  return;
}


