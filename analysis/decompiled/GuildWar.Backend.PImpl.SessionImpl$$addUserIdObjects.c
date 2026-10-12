// name: GuildWar.Backend.PImpl.SessionImpl$$addUserIdObjects
// entry: 01b318a4

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

long * GuildWar_Backend_PImpl_SessionImpl__addUserIdObjects(undefined8 param_1,long param_2)

{
  long *plVar1;
  long lVar2;
  undefined8 uVar3;
  long *plVar4;
  long lVar5;
  undefined8 uStack_38;
  
  if ((bRam00000000049fbe01 & 1) == 0) {
    func_0x010c65d4(0xc034);
    bRam00000000049fbe01 = 1;
  }
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  plVar1 = (long *)func_0x01101ad4(_class_object___TypeInfo,*(int *)(param_2 + 0x18) + 2);
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  System_Array__CopyTo(param_2,plVar1,0,0);
  if (plVar1 == (long *)0x0) {
    func_0x010eec64(0);
    func_0x010eec64(0);
  }
  if ((_str_user_id != 0) &&
     (lVar2 = func_0x010fdfcc(_str_user_id,*(undefined8 *)(*plVar1 + 0x40)), lVar2 == 0)) {
    uVar3 = func_0x010efe90();
    func_0x010eebec(uVar3,0,0);
  }
  lVar5 = _str_user_id;
  lVar2 = (plVar1[3] << 0x20) + -0x200000000;
  if ((uint)plVar1[3] <= (uint)((ulong)lVar2 >> 0x20)) {
    uVar3 = func_0x010ef850();
    func_0x010eebec(uVar3,0,0);
  }
  plVar1[(lVar2 >> 0x20) + 4] = lVar5;
  lVar2 = MyUserData__get_instance(0,0);
  if (lVar2 == 0) {
    func_0x010eec64(0);
  }
  plVar4 = (long *)UserData__get_info(lVar2,0);
  if (plVar4 == (long *)0x0) {
    func_0x010eec64(0);
  }
  uStack_38 = (**(code **)(*plVar4 + 0x1a0))(plVar4,*(undefined8 *)(*plVar4 + 0x1a8));
  lVar2 = func_0x010fdd08(_class_ulong_TypeInfo,&uStack_38);
  if (plVar1 == (long *)0x0) {
    func_0x010eec64(0);
  }
  if ((lVar2 != 0) && (lVar5 = func_0x010fdfcc(lVar2,*(undefined8 *)(*plVar1 + 0x40)), lVar5 == 0))
  {
    uVar3 = func_0x010efe90();
    func_0x010eebec(uVar3,0,0);
  }
  lVar5 = (plVar1[3] << 0x20) + -0x100000000;
  if ((uint)plVar1[3] <= (uint)((ulong)lVar5 >> 0x20)) {
    uVar3 = func_0x010ef850();
    func_0x010eebec(uVar3,0,0);
  }
  plVar1[(lVar5 >> 0x20) + 4] = lVar2;
  return plVar1;
}


