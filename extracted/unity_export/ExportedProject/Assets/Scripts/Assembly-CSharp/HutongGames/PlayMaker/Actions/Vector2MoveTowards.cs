namespace HutongGames.PlayMaker.Actions
{
	public class Vector2MoveTowards : FsmStateAction
	{
		[RequiredField]
		public FsmVector2 source;

		public FsmVector2 target;

		public FsmFloat maxSpeed;

		public FsmFloat finishDistance;

		public FsmEvent finishEvent;

		public override void Reset()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoMoveTowards()
		{
		}
	}
}
