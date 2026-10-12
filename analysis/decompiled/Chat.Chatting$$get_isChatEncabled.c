// name: Chat.Chatting$$get_isChatEncabled
// entry: 01d2965c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

bool Chat_Chatting__get_isChatEncabled(void)

{
  bool bVar1;
  int iVar2;
  long lVar3;
  ulong uVar4;
  
  if ((bRam00000000049fcf12 & 1) == 0) {
    func_0x010c65d4(0x2613);
    bRam00000000049fcf12 = 1;
  }
  if (((*(byte *)(_class_Singleton<UserOptions>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<UserOptions>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar3 = Singleton<iTweenManager>__get_instance(0,_mm_Method_Singleton<UserOptions>_get_instance__)
  ;
  if (lVar3 == 0) {
    func_0x010eec64(0);
  }
  uVar4 = UserOptions__get_chatEnabled(lVar3,0);
  if ((uVar4 & 1) == 0) {
    bVar1 = false;
  }
  else {
    lVar3 = MyUserData__get_instance(0,0);
    if (lVar3 == 0) {
      func_0x010eec64(0);
    }
    lVar3 = UserData__get_saveflags(lVar3,0);
    if (lVar3 == 0) {
      func_0x010eec64(0);
    }
    iVar2 = UserSaveFlags__GetSaveFlagValue(lVar3,0xbd3,0);
    bVar1 = 0 < iVar2;
  }
  return bVar1;
}


