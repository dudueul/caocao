// name: GuildWarBattleFieldController$$Start
// entry: 01ed25e8

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void GuildWarBattleFieldController__Start(long param_1)

{
  undefined4 uVar1;
  undefined4 uVar2;
  undefined4 uVar3;
  undefined4 uVar4;
  ulong uVar5;
  long *plVar6;
  long lVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  long lVar10;
  undefined8 uVar11;
  undefined8 uStack_90;
  undefined8 uStack_88;
  undefined8 uStack_80;
  undefined8 uStack_78;
  
  if ((bRam00000000049fde55 & 1) == 0) {
    func_0x010c65d4(0x6589);
    bRam00000000049fde55 = 1;
  }
  if (((*(byte *)(_class_GuildWarBattleFieldController_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_GuildWarBattleFieldController_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar7 = **(long **)(_class_GuildWarBattleFieldController_TypeInfo + 0xb8);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  GuildWarBattleFieldController__Init(lVar7);
  if (((*(byte *)(_class_GuildWarBattleFieldTileMap_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_GuildWarBattleFieldTileMap_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar7 = **(long **)(_class_GuildWarBattleFieldTileMap_TypeInfo + 0xb8);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  GuildWarBattleFieldTileMap__Init(lVar7,0);
  if (((*(byte *)(_class_GuildWarBattleFieldCameraController_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_GuildWarBattleFieldCameraController_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar7 = **(long **)(_class_GuildWarBattleFieldCameraController_TypeInfo + 0xb8);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  GuildWarBattleFieldCameraController__Init(lVar7);
  uVar8 = *(undefined8 *)(param_1 + 0x38);
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar5 = UnityEngine_Object__op_Inequality(0,uVar8,0,0);
  if ((uVar5 & 1) == 0) goto code_r0x01ed2a90;
  lVar7 = *(long *)(param_1 + 0x38);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  UIGuildWarMinimap__Init(lVar7,0);
  lVar7 = *(long *)(param_1 + 0x38);
  if (((*(byte *)(_class_GuildWarBattleFieldTileMap_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_GuildWarBattleFieldTileMap_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar10 = **(long **)(_class_GuildWarBattleFieldTileMap_TypeInfo + 0xb8);
  if (lVar10 == 0) {
    func_0x010eec64(0);
    uVar8 = uRam0000000000000030;
    lVar10 = **(long **)(_class_GuildWarBattleFieldTileMap_TypeInfo + 0xb8);
    if (lVar10 == 0) {
      func_0x010eec64(0);
      lVar10 = 0;
    }
  }
  else {
    uVar8 = *(undefined8 *)(lVar10 + 0x30);
  }
  uVar11 = GuildWarBattleFieldTileMap__get_cameraSize(lVar10,0);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  UIGuildWarMinimap__SetMinimapTileSize(uVar11,lVar7,uVar8,0);
  lVar7 = *(long *)(param_1 + 0x38);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  UIGuildWarMinimap__ShowMinimap(lVar7,0);
  lVar7 = *(long *)(param_1 + 0x38);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  uVar8 = _mm_Method_GuildWarBattleFieldController_OnMinimapClicked__;
  uVar9 = *(undefined8 *)(lVar7 + 0xf8);
  uVar11 = func_0x010fdda8(_class_System_Action<Vector3>_TypeInfo);
  System_Action<Vector3>___ctor(uVar11,param_1,uVar8,_mm_Method_System_Action<Vector3>__ctor__);
  plVar6 = (long *)System_Delegate__Remove(0,uVar9,uVar11,0);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  if (plVar6 == (long *)0x0) {
code_r0x01ed28ac:
    plVar6 = (long *)0x0;
  }
  else if (*plVar6 != _class_System_Action<Vector3>_TypeInfo) {
    func_0x0111ef28(&uStack_90,*(undefined8 *)(*plVar6 + 0x40));
    uVar8 = func_0x010ef81c(uStack_90);
    func_0x010eebec(uVar8,0,0);
    func_0x036457e0(&uStack_90);
    goto code_r0x01ed28ac;
  }
  *(long **)(lVar7 + 0xf8) = plVar6;
  lVar7 = *(long *)(param_1 + 0x38);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  uVar8 = _mm_Method_GuildWarBattleFieldController_OnMinimapClicked__;
  uVar9 = *(undefined8 *)(lVar7 + 0xf8);
  uVar11 = func_0x010fdda8(_class_System_Action<Vector3>_TypeInfo);
  System_Action<Vector3>___ctor(uVar11,param_1,uVar8,_mm_Method_System_Action<Vector3>__ctor__);
  plVar6 = (long *)System_Delegate__Combine(0,uVar9,uVar11,0);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  if (plVar6 == (long *)0x0) {
code_r0x01ed2948:
    plVar6 = (long *)0x0;
  }
  else if (*plVar6 != _class_System_Action<Vector3>_TypeInfo) {
    func_0x0111ef28(&uStack_88,*(undefined8 *)(*plVar6 + 0x40));
    uVar8 = func_0x010ef81c(uStack_88);
    func_0x010eebec(uVar8,0,0);
    func_0x036457e0(&uStack_88);
    goto code_r0x01ed2948;
  }
  *(long **)(lVar7 + 0xf8) = plVar6;
  lVar7 = *(long *)(param_1 + 0x38);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  uVar8 = _mm_Method_GuildWarBattleFieldController_OnMinimapDragging__;
  uVar9 = *(undefined8 *)(lVar7 + 0x100);
  uVar11 = func_0x010fdda8(_class_System_Action<Vector3>_TypeInfo);
  System_Action<Vector3>___ctor(uVar11,param_1,uVar8,_mm_Method_System_Action<Vector3>__ctor__);
  plVar6 = (long *)System_Delegate__Remove(0,uVar9,uVar11,0);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  if (plVar6 == (long *)0x0) {
code_r0x01ed29ec:
    plVar6 = (long *)0x0;
  }
  else if (*plVar6 != _class_System_Action<Vector3>_TypeInfo) {
    func_0x0111ef28(&uStack_80,*(undefined8 *)(*plVar6 + 0x40));
    uVar8 = func_0x010ef81c(uStack_80);
    func_0x010eebec(uVar8,0,0);
    func_0x036457e0(&uStack_80);
    goto code_r0x01ed29ec;
  }
  *(long **)(lVar7 + 0x100) = plVar6;
  lVar7 = *(long *)(param_1 + 0x38);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  uVar8 = _mm_Method_GuildWarBattleFieldController_OnMinimapDragging__;
  uVar9 = *(undefined8 *)(lVar7 + 0x100);
  uVar11 = func_0x010fdda8(_class_System_Action<Vector3>_TypeInfo);
  System_Action<Vector3>___ctor(uVar11,param_1,uVar8,_mm_Method_System_Action<Vector3>__ctor__);
  plVar6 = (long *)System_Delegate__Combine(0,uVar9,uVar11,0);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  if (plVar6 == (long *)0x0) {
code_r0x01ed2a88:
    plVar6 = (long *)0x0;
  }
  else if (*plVar6 != _class_System_Action<Vector3>_TypeInfo) {
    func_0x0111ef28(&uStack_78,*(undefined8 *)(*plVar6 + 0x40));
    uVar8 = func_0x010ef81c(uStack_78);
    func_0x010eebec(uVar8,0,0);
    func_0x036457e0(&uStack_78);
    goto code_r0x01ed2a88;
  }
  *(long **)(lVar7 + 0x100) = plVar6;
code_r0x01ed2a90:
  if (((*(byte *)(_class_SceneSingleton<GuildWarBattleFieldManager>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_SceneSingleton<GuildWarBattleFieldManager>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar7 = SceneSingleton<WorldBossBattleController>__get_instance
                    (0,_mm_Method_SceneSingleton<GuildWarBattleFieldManager>_get_instance__);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  uVar5 = GuildWarBattleFieldManager__Init(lVar7);
  if ((uVar5 & 1) != 0) {
    uVar8 = *(undefined8 *)(param_1 + 0x40);
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar5 = UnityEngine_Object__op_Inequality(0,uVar8,0,0);
    if ((uVar5 & 1) != 0) {
      lVar7 = *(long *)(param_1 + 0x40);
      if (((*(byte *)(_class_SceneSingleton<GuildWarBattleFieldManager>_TypeInfo + 0x12a) & 1) != 0)
         && (*(int *)(_class_SceneSingleton<GuildWarBattleFieldManager>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar10 = SceneSingleton<WorldBossBattleController>__get_instance
                         (0,_mm_Method_SceneSingleton<GuildWarBattleFieldManager>_get_instance__);
      if (lVar10 == 0) {
        func_0x010eec64(0);
      }
      if (*(long *)(lVar10 + 0x28) == 0) {
        uVar8 = 0;
      }
      else {
        uVar8 = GuildWar_WorldData__get_myGuild();
      }
      lVar10 = SceneSingleton<WorldBossBattleController>__get_instance
                         (0,_mm_Method_SceneSingleton<GuildWarBattleFieldManager>_get_instance__);
      if (lVar10 == 0) {
        func_0x010eec64(0);
      }
      plVar6 = *(long **)(lVar10 + 0x28);
      if (plVar6 == (long *)0x0) {
        uVar1 = 0xffffffff;
      }
      else {
        uVar1 = (**(code **)(*plVar6 + 0x270))(plVar6,*(undefined8 *)(*plVar6 + 0x278));
      }
      lVar10 = SceneSingleton<WorldBossBattleController>__get_instance
                         (0,_mm_Method_SceneSingleton<GuildWarBattleFieldManager>_get_instance__);
      if (lVar10 == 0) {
        func_0x010eec64(0);
      }
      plVar6 = *(long **)(lVar10 + 0x28);
      if (plVar6 == (long *)0x0) {
        uVar2 = 0xffffffff;
      }
      else {
        uVar2 = (**(code **)(*plVar6 + 0x280))(plVar6,*(undefined8 *)(*plVar6 + 0x288));
      }
      lVar10 = SceneSingleton<WorldBossBattleController>__get_instance
                         (0,_mm_Method_SceneSingleton<GuildWarBattleFieldManager>_get_instance__);
      if (lVar10 == 0) {
        func_0x010eec64(0);
      }
      plVar6 = *(long **)(lVar10 + 0x28);
      if (plVar6 == (long *)0x0) {
        uVar3 = 0xffffffff;
      }
      else {
        uVar3 = (**(code **)(*plVar6 + 0x2a0))(plVar6,*(undefined8 *)(*plVar6 + 0x2a8));
      }
      lVar10 = SceneSingleton<WorldBossBattleController>__get_instance
                         (0,_mm_Method_SceneSingleton<GuildWarBattleFieldManager>_get_instance__);
      if (lVar10 == 0) {
        func_0x010eec64(0);
      }
      plVar6 = *(long **)(lVar10 + 0x28);
      if (plVar6 == (long *)0x0) {
        uVar4 = 0xffffffff;
      }
      else {
        uVar4 = (**(code **)(*plVar6 + 0x2b0))(plVar6,*(undefined8 *)(*plVar6 + 0x2b8));
      }
      if (lVar7 == 0) {
        func_0x010eec64(0);
      }
      UIGuildWarCorpsManagement__init(lVar7,uVar8,uVar1,uVar2,uVar3,uVar4,0);
    }
  }
  return;
}


