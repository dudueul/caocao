using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class PlayAnimation : BaseAnimationAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString animName;

		public PlayMode playMode;

		public FsmFloat blendTime;

		public FsmEvent finishEvent;

		public FsmEvent loopEvent;

		public bool stopOnExit;

		private AnimationState anim;

		private float prevAnimtTime;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoPlayAnimation()
		{
		}

		public override void OnUpdate()
		{
		}

		public override void OnExit()
		{
		}

		private void StopAnimation()
		{
		}
	}
}
