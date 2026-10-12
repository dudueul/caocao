// name: Chat.Chatting$$<OnGuildLeave>m__4
// entry: 01d322e8

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

bool Chat_Chatting__<OnGuildLeave>m__4(undefined8 param_1,long param_2)

{
  int iVar1;
  
  if ((bRam00000000049fcf66 & 1) == 0) {
    func_0x010c65d4(0x25fb);
    bRam00000000049fcf66 = 1;
  }
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  iVar1 = System_String__IndexOf(param_2,_str_guildRoom_,0);
  return iVar1 != -1;
}


