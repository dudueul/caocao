namespace HutongGames.PlayMaker.Actions
{
	public abstract class QuaternionBaseAction : FsmStateAction
	{
		public enum everyFrameOptions
		{
			Update = 0,
			FixedUpdate = 1,
			LateUpdate = 2
		}

		public bool everyFrame;

		public everyFrameOptions everyFrameOption;

		public override void Awake()
		{
		}
	}
}
