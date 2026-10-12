using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetAnimatorCurrentStateInfo : FsmStateActionAnimatorBase
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmInt layerIndex;

		public FsmString name;

		public FsmInt nameHash;

		public FsmInt fullPathHash;

		public FsmInt shortPathHash;

		public FsmInt tagHash;

		public FsmBool isStateLooping;

		public FsmFloat length;

		public FsmFloat normalizedTime;

		public FsmInt loopCount;

		public FsmFloat currentLoopProgress;

		private Animator _animator;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnActionUpdate()
		{
		}

		private void GetLayerInfo()
		{
		}
	}
}
