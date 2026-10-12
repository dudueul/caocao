// name: Chat.Chatting$$TrimChat
// entry: 01d2fb38

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__TrimChat(long param_1,undefined8 param_2,undefined8 param_3,long param_4)

{
  long lVar1;
  int iVar2;
  ulong uVar3;
  undefined8 uVar4;
  long lVar5;
  ulong uVar6;
  undefined1 auVar7 [16];
  
  if ((bRam00000000049fcf44 & 1) == 0) {
    func_0x010c65d4(0x25f7);
    bRam00000000049fcf44 = 1;
  }
  lVar5 = *(long *)(param_1 + 0x70);
  if (lVar5 == 0) {
    func_0x010eec64(0);
  }
  uVar3 = System_Collections_Generic_Dictionary<ListenerPrefix,_HttpListener>__ContainsKey
                    (lVar5,param_2,
                     _mm_Method_System_Collections_Generic_Dictionary<string,_List<EventArgs>>_ContainsKey__
                    );
  if ((uVar3 & 1) != 0) {
    lVar5 = *(long *)(param_1 + 0x70);
    if (lVar5 == 0) {
      func_0x010eec64(0);
    }
    lVar5 = System_Collections_Generic_Dictionary<object,_object>__get_Item
                      (lVar5,param_2,
                       _mm_Method_System_Collections_Generic_Dictionary<string,_List<EventArgs>>_get_Item__
                      );
    if ((lVar5 != 0) &&
       (iVar2 = System_Collections_Generic_List<tk2dUILayoutItem>__get_Count
                          (lVar5,_mm_Method_System_Collections_Generic_List<EventArgs>_get_Count__),
       lVar1 = _mm_Method_System_Collections_Generic_List<EventArgs>_RemoveAt__, 199 < iVar2)) {
      if ((bRam0000000004a09250 & 1) == 0) {
        func_0x010c65d4(0x7e85);
        bRam0000000004a09250 = 1;
      }
      if (*(int *)(lVar5 + 0x18) == 0) {
        uVar4 = func_0x010fdda8(_class_System_ArgumentOutOfRangeException_TypeInfo);
        System_ArgumentOutOfRangeException___ctor(uVar4,_str_index,0);
        uVar3 = _mm_Method_System_Collections_Generic_List<object>_RemoveAt__;
        func_0x010eebec(uVar4,0);
        auVar7 = func_0x0112148c();
        lVar5 = auVar7._0_8_;
        uVar6 = auVar7._8_8_ & 0xffffffff;
        if (lVar5 == 0) {
          func_0x010eec64(0);
        }
        (*(code *)**(undefined8 **)(*(long *)(*(long *)(param_4 + 0x18) + 0xc0) + 0x118))
                  (lVar5,uVar6,uVar3 & 0xffffffff);
        if (0 < (int)uVar3) {
          if (lVar5 == 0) {
            func_0x010eec64(0);
          }
          (*(code *)**(undefined8 **)(*(long *)(*(long *)(param_4 + 0x18) + 0xc0) + 0x120))
                    (lVar5,uVar6,-(int)uVar3);
          System_Array__Clear(0,*(undefined8 *)(lVar5 + 0x10),*(undefined4 *)(lVar5 + 0x18),
                              uVar3 & 0xffffffff,0);
          *(int *)(lVar5 + 0x1c) = *(int *)(lVar5 + 0x1c) + 1;
        }
        return;
      }
      (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar1 + 0x18) + 0xc0) + 0x120))
                (lVar5,0,0xffffffff);
      System_Array__Clear(0,*(undefined8 *)(lVar5 + 0x10),*(undefined4 *)(lVar5 + 0x18),1,0);
      *(int *)(lVar5 + 0x1c) = *(int *)(lVar5 + 0x1c) + 1;
      return;
    }
  }
  return;
}


