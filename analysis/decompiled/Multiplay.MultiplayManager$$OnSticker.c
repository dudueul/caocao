// name: Multiplay.MultiplayManager$$OnSticker
// entry: 013a9798

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnSticker(long param_1,long param_2)

{
  int iVar1;
  uint uVar2;
  undefined8 uVar3;
  undefined8 uVar4;
  long lVar5;
  long lVar6;
  long lVar7;
  
  if ((bRam00000000049f7b12 & 1) == 0) {
    func_0x010c65d4(0x8a6a);
    bRam00000000049f7b12 = 1;
  }
  if ((*(long *)(param_1 + 0x40) != 0) && (*(long *)(param_1 + 0x48) != 0)) {
    if (param_2 == 0) {
      func_0x010eec64(0);
    }
    uVar3 = SocketIO_SocketIOEvent__get_data(param_2,0);
    uVar4 = func_0x010fdda8(_class_BattlePlay_BattleSticker_TypeInfo);
    BattlePlay_BattleSticker___ctor(uVar4,uVar3,0);
    if (((*(byte *)(_class_Singleton<BattlePlayManager>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Singleton<BattlePlayManager>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar5 = Singleton<iTweenManager>__get_instance
                      (0,_mm_Method_Singleton<BattlePlayManager>_get_instance__);
    if (lVar5 == 0) {
      func_0x010eec64(0);
    }
    lVar5 = *(long *)(lVar5 + 0x18);
    if (lVar5 == 0) {
      func_0x010eec64(0,uVar4,0);
    }
    if ((bRam00000000049fadf0 & 1) == 0) {
      func_0x010c65d4(0x203d);
      bRam00000000049fadf0 = 1;
    }
    lVar5 = *(long *)(lVar5 + 0x68);
    if (lVar5 == 0) {
      func_0x010eec64(0);
    }
    lVar6 = _mm_Method_System_Collections_Generic_List<BattleSticker>_Add__;
    iVar1 = *(int *)(lVar5 + 0x18);
    lVar7 = *(long *)(lVar5 + 0x10);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    if (iVar1 == *(int *)(lVar7 + 0x18)) {
      if (lVar5 == 0) {
        func_0x010eec64(0);
      }
      (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar6 + 0x18) + 0xc0) + 0x88))(lVar5,1);
    }
    uVar2 = *(uint *)(lVar5 + 0x18);
    lVar6 = *(long *)(lVar5 + 0x10);
    *(uint *)(lVar5 + 0x18) = uVar2 + 1;
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    if (*(uint *)(lVar6 + 0x18) <= uVar2) {
      uVar3 = func_0x010ef850();
      func_0x010eebec(uVar3,0,0);
    }
    *(undefined8 *)(lVar6 + (long)(int)uVar2 * 8 + 0x20) = uVar4;
    *(int *)(lVar5 + 0x1c) = *(int *)(lVar5 + 0x1c) + 1;
    return;
  }
  return;
}


