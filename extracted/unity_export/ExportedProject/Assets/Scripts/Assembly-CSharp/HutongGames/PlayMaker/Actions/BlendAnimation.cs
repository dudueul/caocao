using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class BlendAnimation : BaseAnimationAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmString animName;

		[RequiredField]
		public FsmFloat targetWeight;

		[RequiredField]
		public FsmFloat time;

		public FsmEvent finishEvent;

		private DelayedEvent delayedFinishEvent;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoBlendAnimation(GameObject go)
		{
		}
	}
}
