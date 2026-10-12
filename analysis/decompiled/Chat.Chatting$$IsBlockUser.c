// name: Chat.Chatting$$IsBlockUser
// entry: 01d2ed8c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

bool Chat_Chatting__IsBlockUser(long param_1,undefined8 param_2)

{
  undefined8 uVar1;
  long lVar2;
  undefined8 uVar3;
  long lVar4;
  
  if ((bRam00000000049fcf46 & 1) == 0) {
    func_0x010c65d4(0x25d3);
    bRam00000000049fcf46 = 1;
  }
  lVar2 = func_0x010fdda8(_class_Chat_Chatting_<IsBlockUser>c__AnonStorey1_TypeInfo);
  System_Object___ctor(lVar2,0);
  if (lVar2 == 0) {
    func_0x010eec64(0);
  }
  *(undefined8 *)(lVar2 + 0x10) = param_2;
  uVar1 = _mm_Method_Chat_Chatting_<IsBlockUser>c__AnonStorey1_<>m__0__;
  lVar4 = *(long *)(param_1 + 0x50);
  uVar3 = func_0x010fdda8(_class_System_Predicate<ChatBlockUser>_TypeInfo);
  System_Predicate<Action>___ctor
            (uVar3,lVar2,uVar1,_mm_Method_System_Predicate<ChatBlockUser>__ctor__);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  lVar2 = System_Collections_Generic_List<object>__Find
                    (lVar4,uVar3,_mm_Method_System_Collections_Generic_List<ChatBlockUser>_Find__);
  return lVar2 != 0;
}


