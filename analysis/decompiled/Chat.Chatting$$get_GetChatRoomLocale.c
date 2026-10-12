// name: Chat.Chatting$$get_GetChatRoomLocale
// entry: 01d27c24

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

int Chat_Chatting__get_GetChatRoomLocale(void)

{
  int iVar1;
  int iVar2;
  long lVar3;
  ulong uVar4;
  undefined4 uVar5;
  undefined8 uVar6;
  
  if ((bRam00000000049fcef5 & 1) == 0) {
    func_0x010c65d4(0x2610);
    bRam00000000049fcef5 = 1;
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
  uVar4 = UserOptions__get_chatRoomLocaleEnabled(lVar3,0);
  uVar5 = 3;
  if ((uVar4 & 1) == 0) {
    uVar5 = 0;
  }
  if (((*(byte *)(_class_Chat_Chatting_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Chat_Chatting_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar6 = **(undefined8 **)(_class_Chat_Chatting_TypeInfo + 0xb8);
  if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredPrefs_TypeInfo + 0x12a) & 1) != 0
      ) && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredPrefs_TypeInfo + 0xd8) == 0))
  {
    func_0x010da55c();
  }
  iVar2 = CodeStage_AntiCheat_ObscuredTypes_ObscuredPrefs__GetInt(0,uVar6,uVar5,0);
  iVar1 = 3;
  if (iVar2 != 4) {
    iVar1 = iVar2;
  }
  return iVar1;
}


