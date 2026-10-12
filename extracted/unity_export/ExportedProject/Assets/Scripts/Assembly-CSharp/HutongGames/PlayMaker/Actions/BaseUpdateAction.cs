namespace HutongGames.PlayMaker.Actions
{
	public abstract class BaseUpdateAction : FsmStateAction
	{
		public enum UpdateType
		{
			OnUpdate = 0,
			OnLateUpdate = 1,
			OnFixedUpdate = 2
		}

		public bool everyFrame;

		public UpdateType updateType;

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

		public override void OnLateUpdate()
		{
		}

		public override void OnFixedUpdate()
		{
		}
	}
}
