// name: Chat.Chatting$$<OnGuildError>m__5
// entry: 01d32348

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

bool Chat_Chatting__<OnGuildError>m__5(undefined8 param_1,long param_2)

{
  int iVar1;
  
  if ((bRam00000000049fcf67 & 1) == 0) {
    func_0x010c65d4(0x25fa);
    bRam00000000049fcf67 = 1;
  }
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  iVar1 = System_String__IndexOf(param_2,_str_guildRoom_,0);
  return iVar1 != -1;
}


