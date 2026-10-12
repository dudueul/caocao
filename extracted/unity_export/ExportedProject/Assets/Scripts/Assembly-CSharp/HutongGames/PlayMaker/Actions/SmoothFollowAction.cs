using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SmoothFollowAction : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmGameObject targetObject;

		[RequiredField]
		public FsmFloat distance;

		[RequiredField]
		public FsmFloat height;

		[RequiredField]
		public FsmFloat heightDamping;

		[RequiredField]
		public FsmFloat rotationDamping;

		private GameObject cachedObject;

		private Transform myTransform;

		private GameObject cachedTarget;

		private Transform targetTransform;

		public override void Reset()
		{
		}

		public override void OnPreprocess()
		{
		}

		public override void OnLateUpdate()
		{
		}
	}
}
