# Rickety Water Wheel

This adds just one block: a Water Wheel which acts exactly like Create's small Water Wheel, but with limited durability.

No recipe is provided; this is intended for pack developers who would probably want to replace Create's water wheel with
this one for early-game progression.

Durability is drained each tick that this Water Wheel is connected to a shaft network with any stress impact on it; the
Clutch will be of use to players to disconnect it from the network when they're not using its stress capacity, prolonging
its life.  Durability is in ticks; default is 36,000 ticks, which is 30 minutes of active usage. This can be changed in
config; see `max_durability` in `ricketyww-common.toml`.

Stress capacity is 32.0, exactly the same as the normal Create water wheel; this can also be changed in config
(`water_wheel_stress_capacity`).