// name: Chat.Chatting$$OnConnect
// entry: 01d2d0f0

void Chat_Chatting__OnConnect(long param_1)

{
  long lVar1;
  long *plVar2;
  undefined8 uVar3;
  undefined8 uVar4;
  undefined8 uStack_28;
  
  uStack_28 = 0;
  lVar1 = MyUserData__get_instance(0,0);
  if (lVar1 == 0) {
    func_0x010eec64(0);
  }
  plVar2 = (long *)UserData__get_info(lVar1,0);
  if (plVar2 == (long *)0x0) {
    func_0x010eec64(0);
  }
  uStack_28 = (**(code **)(*plVar2 + 0x1a0))(plVar2,*(undefined8 *)(*plVar2 + 0x1a8));
  lVar1 = MyUserData__get_instance(0,0);
  if (lVar1 == 0) {
    func_0x010eec64(0);
  }
  lVar1 = UserData__get_info(lVar1,0);
  if (lVar1 == 0) {
    func_0x010eec64(0);
  }
  uVar3 = UserInfo__get_nationName(lVar1,0);
  uVar4 = func_0x02d2a574(&uStack_28,0);
  Chat_Chatting__RegisterChattingUser(param_1,uVar4,uVar3);
  if (*(long *)(param_1 + 0x98) != 0) {
    System_EventHandler__Invoke(*(long *)(param_1 + 0x98),param_1,0,0);
  }
  return;
}


