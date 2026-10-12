namespace HutongGames.PlayMaker.Actions
{
	public abstract class FsmStateActionAnimatorBase : FsmStateAction
	{
		public enum AnimatorFrameUpdateSelector
		{
			OnUpdate = 0,
			OnAnimatorMove = 1,
			OnAnimatorIK = 2
		}

		public bool everyFrame;

		public AnimatorFrameUpdateSelector everyFrameOption;

		protected int IklayerIndex;

		public abstract void OnActionUpdate();

		public override void Reset()
		{
		}

		public override void OnPreprocess()
		{
		}

		public override void OnUpdate()
		{
		}

		public override void DoAnimatorMove()
		{
		}

		public override void DoAnimatorIK(int layerIndex)
		{
		}
	}
}
