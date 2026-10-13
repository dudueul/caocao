// name: Multiplay.MultiplayManager$$OnStepResponsed
// entry: 013ab9b4

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnStepResponsed(long param_1,long param_2)

{
  ushort uVar1;
  undefined4 uVar2;
  uint uVar3;
  ulong uVar4;
  undefined8 uVar5;
  code *UNRECOVERED_JUMPTABLE_00;
  ulong uVar6;
  long *plVar7;
  undefined8 uVar8;
  undefined8 *puVar9;
  long lVar10;
  int *piVar11;
  long lVar12;
  
  if ((bRam00000000049f7b20 & 1) == 0) {
    func_0x010c65d4(0x8a69);
    bRam00000000049f7b20 = 1;
  }
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar4 = Multiplay_MultiplayManager__get_isWatchingBattle();
  if ((uVar4 & 1) != 0) {
    if (param_2 == 0) {
      func_0x010eec64(0);
    }
    uVar5 = SocketIO_SocketIOEvent__get_data(param_2,0);
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_Util_TypeInfo);
    }
    uVar2 = Util__GetData<Step>(0,uVar5,_str_step,8,_mm_Method_Util_GetData<Step>__);
    lVar12 = *(long *)(param_1 + 0x130);
    if (lVar12 != 0) {
      if (param_2 == 0) {
        func_0x010eec64(0);
      }
      uVar5 = SocketIO_SocketIOEvent__get_data(param_2,0);
      if (*(long *)(lVar12 + 0x58) != 0) {
        System_Action<Step,_object>__Invoke
                  (*(long *)(lVar12 + 0x58),uVar2,uVar5,
                   _mm_Method_System_Action<Step,_JsonData>_Invoke__);
      }
      UNRECOVERED_JUMPTABLE_00 = *(code **)(lVar12 + 0x10);
      plVar7 = *(long **)(lVar12 + 0x20);
      puVar9 = *(undefined8 **)(lVar12 + 0x28);
      func_0x010da364(puVar9);
      uVar3 = func_0x01101d6c(puVar9);
      if ((uVar3 & 1) == 0) {
        if (*(char *)((long)puVar9 + 0x4e) != '\x02') {
                    /* WARNING: Could not recover jumptable at 0x029af3b4. Too many branches */
                    /* WARNING: Treating indirect jump as call */
          (*UNRECOVERED_JUMPTABLE_00)(0,plVar7,uVar2,uVar5,puVar9);
          return;
        }
        plVar7 = (long *)0x0;
      }
      else if ((*(short *)((long)puVar9 + 0x4c) != -1) &&
              (((plVar7 == (long *)0x0 || ((*(byte *)(*plVar7 + 0x10d) & 1) == 0)) &&
               (*(long *)(lVar12 + 0x18) != 0)))) {
        uVar4 = func_0x01101d58(puVar9);
        func_0x01101dd4(puVar9);
        uVar6 = func_0x010ba65c();
        if ((uVar4 & 1) == 0) {
          uVar1 = *(ushort *)((long)puVar9 + 0x4c);
          if ((uVar6 & 1) == 0) {
            lVar12 = *plVar7 + (ulong)uVar1 * 0x10;
            UNRECOVERED_JUMPTABLE_00 = *(code **)(lVar12 + 0x130);
            puVar9 = *(undefined8 **)(lVar12 + 0x138);
          }
          else {
            lVar12 = func_0x01101dd4(puVar9);
            lVar10 = *plVar7;
            if ((ulong)*(ushort *)(lVar10 + 0x122) != 0) {
              uVar4 = 0;
              piVar11 = (int *)(*(long *)(lVar10 + 0xb0) + 8);
              do {
                if (*(long *)(piVar11 + -2) == lVar12) {
                  puVar9 = (undefined8 *)(lVar10 + (ulong)(*piVar11 + (uint)uVar1) * 0x10 + 0x130);
                  goto code_r0x029af480;
                }
                uVar4 = uVar4 + 1;
                piVar11 = piVar11 + 4;
              } while (uVar4 < *(ushort *)(lVar10 + 0x122));
            }
            puVar9 = (undefined8 *)func_0x010bc584(plVar7,lVar12,(ulong)uVar1);
code_r0x029af480:
            UNRECOVERED_JUMPTABLE_00 = (code *)*puVar9;
            puVar9 = (undefined8 *)puVar9[1];
          }
        }
        else {
          if ((uVar6 & 1) == 0) {
            uVar8 = *(undefined8 *)
                     (*plVar7 + (ulong)*(ushort *)((long)puVar9 + 0x4c) * 0x10 + 0x138);
          }
          else {
            lVar12 = *plVar7;
            if ((ulong)*(ushort *)(lVar12 + 0x122) != 0) {
              uVar4 = 0;
              piVar11 = (int *)(*(long *)(lVar12 + 0xb0) + 8);
              do {
                if (*(long *)(piVar11 + -2) == puVar9[3]) {
                  lVar12 = lVar12 + (ulong)(*piVar11 + (uint)*(ushort *)((long)puVar9 + 0x4c)) *
                                    0x10 + 0x130;
                  goto code_r0x029af448;
                }
                uVar4 = uVar4 + 1;
                piVar11 = piVar11 + 4;
              } while (uVar4 < *(ushort *)(lVar12 + 0x122));
            }
            lVar12 = func_0x010bc584(plVar7);
code_r0x029af448:
            uVar8 = *(undefined8 *)(lVar12 + 8);
          }
          puVar9 = (undefined8 *)func_0x010da294(uVar8,puVar9);
          UNRECOVERED_JUMPTABLE_00 = (code *)*puVar9;
        }
      }
                    /* WARNING: Could not recover jumptable at 0x029af384. Too many branches */
                    /* WARNING: Treating indirect jump as call */
      (*UNRECOVERED_JUMPTABLE_00)(plVar7,uVar2,uVar5,puVar9);
      return;
    }
  }
  return;
}


