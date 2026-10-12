// name: Chat.Chatting$$OnNotFoundRoomId
// entry: 01d2e384

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__OnNotFoundRoomId(long param_1)

{
  long lVar1;
  uint uVar2;
  int iVar3;
  undefined8 uVar4;
  undefined8 uVar5;
  ulong uVar6;
  long lVar7;
  long lVar8;
  ulong uVar9;
  undefined1 auVar10 [16];
  
  if ((bRam00000000049fcf2c & 1) == 0) {
    func_0x010c65d4(0x25ec);
    bRam00000000049fcf2c = 1;
  }
  if (*(long *)(param_1 + 0xb0) == 0) {
    return;
  }
  System_EventHandler__Invoke(*(long *)(param_1 + 0xb0),param_1,0,0);
  if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar7 = **(long **)(_class_string_TypeInfo + 0xb8);
  uVar4 = func_0x010fdda8(_class_Chat_WhisperResponse_TypeInfo);
  Chat_WhisperResponse___ctor(uVar4,lVar7,lVar7,lVar7,0,2,0);
  if ((bRam00000000049fcf42 & 1) == 0) {
    func_0x010c65d4(0x25ab);
    bRam00000000049fcf42 = 1;
  }
  uVar5 = Chat_Chatting__GetDestRoomId(param_1,uVar4);
  if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_string_TypeInfo);
  }
  uVar2 = System_String__IsNullOrEmpty(0,uVar5,0);
  if ((uVar2 & 1) == 0) {
    Chat_Chatting__AddChat(param_1,_str_all,uVar4);
    Chat_Chatting__TrimChat(param_1,_str_all);
    Chat_Chatting__AddChat(param_1,uVar5,uVar4);
    if ((bRam00000000049fcf44 & 1) == 0) {
      func_0x010c65d4(0x25f7);
      bRam00000000049fcf44 = 1;
    }
    lVar8 = *(long *)(param_1 + 0x70);
    if (lVar8 == 0) {
      func_0x010eec64(0);
    }
    uVar6 = System_Collections_Generic_Dictionary<ListenerPrefix,_HttpListener>__ContainsKey
                      (lVar8,uVar5,
                       _mm_Method_System_Collections_Generic_Dictionary<string,_List<EventArgs>>_ContainsKey__
                      );
    if ((uVar6 & 1) != 0) {
      lVar8 = *(long *)(param_1 + 0x70);
      if (lVar8 == 0) {
        func_0x010eec64(0);
      }
      lVar8 = System_Collections_Generic_Dictionary<object,_object>__get_Item
                        (lVar8,uVar5,
                         _mm_Method_System_Collections_Generic_Dictionary<string,_List<EventArgs>>_get_Item__
                        );
      if ((lVar8 != 0) &&
         (iVar3 = System_Collections_Generic_List<tk2dUILayoutItem>__get_Count
                            (lVar8,_mm_Method_System_Collections_Generic_List<EventArgs>_get_Count__
                            ),
         lVar1 = _mm_Method_System_Collections_Generic_List<EventArgs>_RemoveAt__, 199 < iVar3)) {
        if ((bRam0000000004a09250 & 1) == 0) {
          func_0x010c65d4(0x7e85);
          bRam0000000004a09250 = 1;
        }
        if (*(int *)(lVar8 + 0x18) == 0) {
          uVar4 = func_0x010fdda8(_class_System_ArgumentOutOfRangeException_TypeInfo);
          System_ArgumentOutOfRangeException___ctor(uVar4,_str_index,0);
          uVar6 = _mm_Method_System_Collections_Generic_List<object>_RemoveAt__;
          func_0x010eebec(uVar4,0);
          auVar10 = func_0x0112148c();
          lVar8 = auVar10._0_8_;
          uVar9 = auVar10._8_8_ & 0xffffffff;
          if (lVar8 == 0) {
            func_0x010eec64(0);
          }
          (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar7 + 0x18) + 0xc0) + 0x118))
                    (lVar8,uVar9,uVar6 & 0xffffffff);
          if (0 < (int)uVar6) {
            if (lVar8 == 0) {
              func_0x010eec64(0);
            }
            (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar7 + 0x18) + 0xc0) + 0x120))
                      (lVar8,uVar9,-(int)uVar6);
            System_Array__Clear(0,*(undefined8 *)(lVar8 + 0x10),*(undefined4 *)(lVar8 + 0x18),
                                uVar6 & 0xffffffff,0);
            *(int *)(lVar8 + 0x1c) = *(int *)(lVar8 + 0x1c) + 1;
          }
          return;
        }
        (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar1 + 0x18) + 0xc0) + 0x120))
                  (lVar8,0,0xffffffff);
        System_Array__Clear(0,*(undefined8 *)(lVar8 + 0x10),*(undefined4 *)(lVar8 + 0x18),1,0);
        *(int *)(lVar8 + 0x1c) = *(int *)(lVar8 + 0x1c) + 1;
        return;
      }
    }
    return;
  }
  return;
}


