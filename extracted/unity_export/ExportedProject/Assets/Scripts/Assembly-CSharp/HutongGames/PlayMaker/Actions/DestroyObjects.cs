namespace HutongGames.PlayMaker.Actions
{
	public class DestroyObjects : FsmStateAction
	{
		[RequiredField]
		public FsmArray gameObjects;

		public FsmFloat delay;

		public FsmBool detachChildren;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}
	}
}
