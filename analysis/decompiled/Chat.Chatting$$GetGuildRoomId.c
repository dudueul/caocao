// name: Chat.Chatting$$GetGuildRoomId
// entry: 01d2f00c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__GetGuildRoomId(undefined8 param_1,undefined8 param_2)

{
  undefined8 uVar1;
  undefined8 uStack_28;
  
  if ((bRam00000000049fcf37 & 1) == 0) {
    func_0x010c65d4(0x25cd);
    bRam00000000049fcf37 = 1;
  }
  uStack_28 = param_2;
  uVar1 = func_0x010fdd08(_class_ulong_TypeInfo,&uStack_28);
  if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_string_TypeInfo);
  }
  System_String__Format(0,_str_guildRoom__0_,uVar1,0);
  return;
}


