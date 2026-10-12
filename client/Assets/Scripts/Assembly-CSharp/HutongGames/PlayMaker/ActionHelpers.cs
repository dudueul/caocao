using System;
using HutongGames.PlayMaker.AnimationEnums;
using UnityEngine;

namespace HutongGames.PlayMaker
{
	public static class ActionHelpers
	{
		public static RaycastHit mousePickInfo;

		private static float mousePickRaycastTime;

		private static float mousePickDistanceUsed;

		private static int mousePickLayerMaskUsed;

		public static Texture2D WhiteTexture
		{
			get
			{
				return null;
			}
		}

		public static Color BlendColor(ColorBlendMode blendMode, Color c1, Color c2)
		{
			return default(Color);
		}

		public static bool IsVisible(GameObject go)
		{
			return false;
		}

		public static GameObject GetOwnerDefault(FsmStateAction action, FsmOwnerDefault ownerDefault)
		{
			return null;
		}

		public static PlayMakerFSM GetGameObjectFsm(GameObject go, string fsmName)
		{
			return null;
		}

		public static int GetRandomWeightedIndex(FsmFloat[] weights)
		{
			return 0;
		}

		public static void AddAnimationClip(GameObject go, AnimationClip animClip)
		{
		}

		public static bool HasAnimationFinished(AnimationState anim, float prevTime, float currentTime)
		{
			return false;
		}

		public static Vector3 GetPosition(FsmGameObject fsmGameObject, FsmVector3 fsmVector3)
		{
			return default(Vector3);
		}

		public static Quaternion GetTargetRotation(RotationOptions option, Transform owner, Transform target, Vector3 rotation)
		{
			return default(Quaternion);
		}

		public static bool GetTargetRotation(RotationOptions option, Transform owner, FsmVector3 rotation, FsmGameObject target, out Quaternion targetRotation)
		{
			targetRotation = default(Quaternion);
			return false;
		}

		private static bool CanEditTargetRotation(RotationOptions option, NamedVariable rotation, FsmGameObject target)
		{
			return false;
		}

		public static Vector3 GetTargetScale(ScaleOptions option, Transform owner, Transform target, Vector3 scale)
		{
			return default(Vector3);
		}

		public static bool GetTargetPosition(PositionOptions option, Transform owner, FsmVector3 position, FsmGameObject target, out Vector3 targetPosition)
		{
			targetPosition = default(Vector3);
			return false;
		}

		private static bool IsValidTargetPosition(PositionOptions option, NamedVariable position, FsmGameObject target)
		{
			return false;
		}

		public static bool CanEditTargetPosition(PositionOptions option, NamedVariable position, FsmGameObject target)
		{
			return false;
		}

		public static Vector3 GetTargetPosition(PositionOptions option, Transform owner, Transform target, Vector3 position)
		{
			return default(Vector3);
		}

		public static bool IsMouseOver(GameObject gameObject, float distance, int layerMask)
		{
			return false;
		}

		public static RaycastHit MousePick(float distance, int layerMask)
		{
			return default(RaycastHit);
		}

		public static GameObject MouseOver(float distance, int layerMask)
		{
			return null;
		}

		private static void DoMousePick(float distance, int layerMask)
		{
		}

		public static int LayerArrayToLayerMask(FsmInt[] layers, bool invert)
		{
			return 0;
		}

		public static bool IsLoopingWrapMode(WrapMode wrapMode)
		{
			return false;
		}

		public static string CheckRayDistance(float rayDistance)
		{
			return null;
		}

		public static string CheckForValidEvent(FsmState state, string eventName)
		{
			return null;
		}

		public static string CheckPhysicsSetup(FsmOwnerDefault ownerDefault)
		{
			return null;
		}

		public static string CheckOwnerPhysicsSetup(GameObject gameObject)
		{
			return null;
		}

		public static string CheckPhysicsSetup(GameObject gameObject)
		{
			return null;
		}

		public static string CheckPhysics2dSetup(FsmOwnerDefault ownerDefault)
		{
			return null;
		}

		public static string CheckOwnerPhysics2dSetup(GameObject gameObject)
		{
			return null;
		}

		public static string CheckPhysics2dSetup(GameObject gameObject)
		{
			return null;
		}

		public static void DebugLog(Fsm fsm, LogLevel logLevel, string text, bool sendToUnityLog = false)
		{
		}

		public static void LogError(string text)
		{
		}

		public static void LogWarning(string text)
		{
		}

		public static string GetValueLabel(INamedVariable variable)
		{
			return null;
		}

		public static string GetValueLabel(Fsm fsm, FsmOwnerDefault ownerDefault)
		{
			return null;
		}

		public static string AutoName(FsmStateAction action, params INamedVariable[] exposedFields)
		{
			return null;
		}

		public static string AutoName(string actionName, params INamedVariable[] exposedFields)
		{
			return null;
		}

		public static string AutoNameRange(FsmStateAction action, NamedVariable min, NamedVariable max)
		{
			return null;
		}

		public static string AutoNameRange(string actionName, NamedVariable min, NamedVariable max)
		{
			return null;
		}

		public static string AutoNameSetVar(FsmStateAction action, NamedVariable var, NamedVariable value)
		{
			return null;
		}

		public static string AutoNameSetVar(string actionName, NamedVariable var, NamedVariable value)
		{
			return null;
		}

		public static string AutoNameConvert(FsmStateAction action, NamedVariable fromVariable, NamedVariable toVariable)
		{
			return null;
		}

		public static string AutoNameConvert(string actionName, NamedVariable fromVariable, NamedVariable toVariable)
		{
			return null;
		}

		public static string AutoNameGetProperty(FsmStateAction action, NamedVariable property, NamedVariable store)
		{
			return null;
		}

		public static string AutoNameGetProperty(string actionName, NamedVariable property, NamedVariable store)
		{
			return null;
		}

		[Obsolete]
		public static void RuntimeError(FsmStateAction action, string error)
		{
		}
	}
}
