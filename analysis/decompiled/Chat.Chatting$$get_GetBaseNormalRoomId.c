// name: Chat.Chatting$$get_GetBaseNormalRoomId
// entry: 01d2f0b0

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined8 Chat_Chatting__get_GetBaseNormalRoomId(long param_1)

{
  long lVar1;
  ulong uVar2;
  undefined8 uVar3;
  undefined4 uStack_24;
  
  if ((bRam00000000049fcf38 & 1) == 0) {
    func_0x010c65d4(0x260f);
    bRam00000000049fcf38 = 1;
  }
  if (((*(byte *)(_class_Singleton<UserOptions>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<UserOptions>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar1 = Singleton<iTweenManager>__get_instance(0,_mm_Method_Singleton<UserOptions>_get_instance__)
  ;
  if (lVar1 == 0) {
    func_0x010eec64(0);
  }
  uVar2 = UserOptions__get_chatRoomLocaleEnabled(lVar1,0);
  uVar3 = _str_normalRoom;
  if ((uVar2 & 1) != 0) {
    lVar1 = *(long *)(param_1 + 0x48);
    if (lVar1 == 0) {
      func_0x010eec64(0);
    }
    uStack_24 = *(undefined4 *)(lVar1 + 0x38);
    uVar3 = func_0x010fdd08(_class_Chat_EChatRoomLocale_TypeInfo,&uStack_24);
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_string_TypeInfo);
    }
    uVar3 = System_String__Format(0,_str_normalRoom_0_,uVar3,0);
  }
  return uVar3;
}


