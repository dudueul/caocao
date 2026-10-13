// name: Multiplay.MultiplayManager$$OnTurnTimeChanged
// entry: 013a9ea8

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnTurnTimeChanged(long param_1,long param_2)

{
  undefined4 uVar1;
  undefined8 uVar2;
  long lVar3;
  long lVar4;
  ulong uVar5;
  int iVar6;
  undefined8 uVar7;
  ulong uVar8;
  undefined4 uStack_54;
  
  if ((bRam00000000049f7b17 & 1) == 0) {
    func_0x010c65d4(0x8a6f);
    bRam00000000049f7b17 = 1;
  }
  uStack_54 = 0;
  if ((*(long *)(param_1 + 0x40) == 0) || (*(long *)(param_1 + 0x48) == 0)) {
    Multiplay_MultiplayManager__SendCancelMatchmaking(param_1,1,0);
  }
  else {
    if (param_2 == 0) {
      func_0x010eec64(0);
    }
    uVar2 = SocketIO_SocketIOEvent__get_data(param_2,0);
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_string_TypeInfo);
    }
    uVar7 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar3 = Util__GetData(0,uVar2,_str_turnTime,uVar7,0);
    lVar4 = func_0x01101ad4(_class_char___TypeInfo,1);
    if (lVar4 == 0) {
      func_0x010eec64(0);
    }
    if (*(int *)(lVar4 + 0x18) == 0) {
      uVar2 = func_0x010ef850();
      func_0x010eebec(uVar2,0,0);
    }
    *(undefined2 *)(lVar4 + 0x20) = 0x2c;
    if (lVar3 == 0) {
      func_0x010eec64(0);
    }
    lVar3 = System_String__Split(lVar3,lVar4,0);
    lVar4 = func_0x010fdda8(_class_System_Collections_Generic_List<int>_TypeInfo);
    System_Collections_Generic_List<int>___ctor
              (lVar4,_mm_Method_System_Collections_Generic_List<int>__ctor__);
    if (lVar3 == 0) {
      func_0x010eec64(0);
    }
    iVar6 = (int)*(undefined8 *)(lVar3 + 0x18);
    if (0 < iVar6) {
      uVar8 = 0;
      do {
        if (lVar3 == 0) {
          func_0x010eec64(0);
        }
        if (*(uint *)(lVar3 + 0x18) <= uVar8) {
          uVar2 = func_0x010ef850();
          func_0x010eebec(uVar2,0,0);
        }
        uVar5 = System_Int32__TryParse(0,*(undefined8 *)(lVar3 + 0x20 + uVar8 * 8),&uStack_54,0);
        uVar1 = uStack_54;
        if ((uVar5 & 1) == 0) {
          return;
        }
        if (lVar4 == 0) {
          func_0x010eec64(0);
        }
        System_Collections_Generic_List<int>__Add
                  (lVar4,uVar1,_mm_Method_System_Collections_Generic_List<int>_Add__);
        uVar8 = uVar8 + 1;
      } while ((long)uVar8 < (long)iVar6);
    }
    if (((*(byte *)(_class_Singleton<BattlePlayManager>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Singleton<BattlePlayManager>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar3 = Singleton<iTweenManager>__get_instance
                      (0,_mm_Method_Singleton<BattlePlayManager>_get_instance__);
    if (lVar3 == 0) {
      func_0x010eec64(0);
    }
    BattlePlay_BattlePlayManager__ChangeTurnTime(lVar3,lVar4,0);
  }
  return;
}


