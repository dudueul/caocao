// name: Chat.Chatting$$RemoveBlockUser
// entry: 01d2fe20

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__RemoveBlockUser(long param_1,undefined8 param_2)

{
  uint uVar1;
  long lVar2;
  undefined8 uVar3;
  undefined8 uVar4;
  ulong uVar5;
  long lVar6;
  long lVar7;
  ulong uVar8;
  undefined1 auVar9 [16];
  
  if ((bRam00000000049fcf48 & 1) == 0) {
    func_0x010c65d4(0x25f1);
    bRam00000000049fcf48 = 1;
  }
  lVar2 = func_0x010fdda8(_class_Chat_Chatting_<RemoveBlockUser>c__AnonStorey2_TypeInfo);
  System_Object___ctor(lVar2,0);
  if (lVar2 == 0) {
    func_0x010eec64(0);
  }
  *(undefined8 *)(lVar2 + 0x10) = param_2;
  uVar4 = _mm_Method_Chat_Chatting_<RemoveBlockUser>c__AnonStorey2_<>m__0__;
  lVar7 = *(long *)(param_1 + 0x50);
  uVar3 = func_0x010fdda8(_class_System_Predicate<ChatBlockUser>_TypeInfo);
  lVar6 = _mm_Method_System_Predicate<ChatBlockUser>__ctor__;
  System_Predicate<Action>___ctor(uVar3,lVar2,uVar4);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  uVar1 = System_Collections_Generic_List<UserUnitGroup_UnitInfo>__FindIndex
                    (lVar7,uVar3,
                     _mm_Method_System_Collections_Generic_List<ChatBlockUser>_FindIndex__);
  if ((int)uVar1 < 0) {
    return;
  }
  lVar2 = *(long *)(param_1 + 0x50);
  if (lVar2 == 0) {
    func_0x010eec64(0);
  }
  lVar7 = _mm_Method_System_Collections_Generic_List<ChatBlockUser>_RemoveAt__;
  if ((bRam0000000004a09250 & 1) == 0) {
    func_0x010c65d4(0x7e85);
    bRam0000000004a09250 = 1;
  }
  if ((-1 < (int)uVar1) && (uVar1 < *(uint *)(lVar2 + 0x18))) {
    (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar7 + 0x18) + 0xc0) + 0x120))
              (lVar2,uVar1,0xffffffff);
    System_Array__Clear(0,*(undefined8 *)(lVar2 + 0x10),*(undefined4 *)(lVar2 + 0x18),1,0);
    *(int *)(lVar2 + 0x1c) = *(int *)(lVar2 + 0x1c) + 1;
    return;
  }
  uVar4 = func_0x010fdda8(_class_System_ArgumentOutOfRangeException_TypeInfo);
  System_ArgumentOutOfRangeException___ctor(uVar4,_str_index,0);
  uVar5 = _mm_Method_System_Collections_Generic_List<object>_RemoveAt__;
  func_0x010eebec(uVar4,0);
  auVar9 = func_0x0112148c();
  lVar2 = auVar9._0_8_;
  uVar8 = auVar9._8_8_ & 0xffffffff;
  if (lVar2 == 0) {
    func_0x010eec64(0);
  }
  (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar6 + 0x18) + 0xc0) + 0x118))
            (lVar2,uVar8,uVar5 & 0xffffffff);
  if (0 < (int)uVar5) {
    if (lVar2 == 0) {
      func_0x010eec64(0);
    }
    (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar6 + 0x18) + 0xc0) + 0x120))
              (lVar2,uVar8,-(int)uVar5);
    System_Array__Clear(0,*(undefined8 *)(lVar2 + 0x10),*(undefined4 *)(lVar2 + 0x18),
                        uVar5 & 0xffffffff,0);
    *(int *)(lVar2 + 0x1c) = *(int *)(lVar2 + 0x1c) + 1;
  }
  return;
}


