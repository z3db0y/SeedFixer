# A bad solution to a bad problem

Seed "fixer" is a Fabric mod intended to allow you to set per-dimension seeds. This is a way to prevent worlds migrated from Paper/Spigot servers from having corrupted chunks, as apparently not specifying a seed in `server.properties` will mean different per-dimension seeds.

## Usage:

- Build using Gradle.
- Edit `seed_nether` and `seed_end` values in `config/seedfixer.toml`
- Enjoy broken generation (your world is traumatized forever)