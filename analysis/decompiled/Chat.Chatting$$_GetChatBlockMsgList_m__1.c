// name: Chat.Chatting$$<GetChatBlockMsgList>m__1
// entry: 01d3205c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined8 Chat_Chatting__<GetChatBlockMsgList>m__1(long param_1,undefined4 param_2)

{
  undefined4 uVar1;
  uint uVar2;
  long lVar3;
  undefined8 uVar4;
  long lVar5;
  long lVar6;
  undefined8 uVar7;
  
  if ((bRam00000000049fcf64 & 1) == 0) {
    func_0x010c65d4(0x25f8);
    bRam00000000049fcf64 = 1;
  }
  lVar3 = func_0x010fdda8(_class_Chat_Chatting_<GetChatBlockMsgList>c__AnonStorey3_TypeInfo);
  System_Object___ctor(lVar3,0);
  if (lVar3 == 0) {
    func_0x010eec64(0);
  }
  *(undefined4 *)(lVar3 + 0x10) = param_2;
  lVar5 = *(long *)(param_1 + 0x70);
  if (lVar5 == 0) {
    func_0x010eec64(0);
  }
  lVar5 = System_Collections_Generic_Dictionary<object,_object>__get_Item
                    (lVar5,_str_all,
                     _mm_Method_System_Collections_Generic_Dictionary<string,_List<EventArgs>>_get_Item__
                    );
  uVar7 = _mm_Method_Chat_Chatting_<GetChatBlockMsgList>c__AnonStorey3_<>m__0__;
  uVar4 = func_0x010fdda8(_class_System_Predicate<EventArgs>_TypeInfo);
  System_Predicate<Action>___ctor(uVar4,lVar3,uVar7,_mm_Method_System_Predicate<EventArgs>__ctor__);
  if (lVar5 == 0) {
    func_0x010eec64(0);
  }
  lVar3 = _mm_Method_System_Collections_Generic_List<EventArgs>_Find__;
  lVar6 = **(long **)(*(long *)(_mm_Method_System_Collections_Generic_List<EventArgs>_Find__ + 0x18)
                     + 0xc0);
  func_0x010bbb2c(lVar6);
  if (((*(byte *)(lVar6 + 0x12a) & 1) != 0) &&
     (lVar6 = **(long **)(*(long *)(lVar3 + 0x18) + 0xc0), func_0x010bbb2c(lVar6),
     *(int *)(lVar6 + 0xd8) == 0)) {
    uVar7 = **(undefined8 **)(*(long *)(lVar3 + 0x18) + 0xc0);
    func_0x010bbb2c(uVar7);
    func_0x010da55c(uVar7);
  }
  (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar3 + 0x18) + 0xc0) + 200))(0,uVar4);
  uVar1 = *(undefined4 *)(lVar5 + 0x18);
  if (lVar5 == 0) {
    func_0x010eec64(0);
  }
  uVar2 = (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar3 + 0x18) + 0xc0) + 0xd0))
                    (lVar5,0,uVar1,uVar4);
  if (uVar2 == 0xffffffff) {
    uVar7 = 0;
  }
  else {
    lVar3 = *(long *)(lVar5 + 0x10);
    if (lVar3 == 0) {
      func_0x010eec64(0);
    }
    if (*(uint *)(lVar3 + 0x18) <= uVar2) {
      uVar7 = func_0x010ef850();
      func_0x010eebec(uVar7,0,0);
    }
    uVar7 = *(undefined8 *)(lVar3 + (long)(int)uVar2 * 8 + 0x20);
  }
  return uVar7;
}


