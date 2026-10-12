using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetAnimatorBoneGameObject : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmEnum bone;

		public FsmGameObject boneGameObject;

		private Animator _animator;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void GetBoneTransform()
		{
		}
	}
}
